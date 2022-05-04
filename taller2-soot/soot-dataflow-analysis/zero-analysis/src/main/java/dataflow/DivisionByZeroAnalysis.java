package dataflow;

import java.util.HashMap;

import dataflow.abs.ZeroAbstractSet;
import dataflow.abs.ZeroAbstractValue;
import soot.Local;
import soot.Unit;
import soot.jimple.DefinitionStmt;
import soot.tagkit.StringTag;
import soot.toolkits.graph.UnitGraph;
import soot.toolkits.scalar.ForwardFlowAnalysis;

/**
 * Division by zero analysis
 */
public class DivisionByZeroAnalysis extends ForwardFlowAnalysis<Unit, ZeroAbstractSet> {

  private HashMap<Unit, Boolean> possibleDivisionByZero = new HashMap<>();

  public DivisionByZeroAnalysis(UnitGraph graph) {
    super(graph);

    doAnalysis();
  }

  protected void flowThrough(ZeroAbstractSet in, Unit unit, ZeroAbstractSet out) {
    // Local all values from input
    out.clear();
    out.putAll(in);

    if (unit instanceof DefinitionStmt) {
      DefinitionStmt definition = (DefinitionStmt) unit;

      // Assume just local variables
      Local variable = (Local) definition.getLeftOp();
      ZeroValueVisitor visitor = new ZeroValueVisitor(in);
      ZeroAbstractValue resolvedValue = visitor.visit(definition.getRightOp()).done();

      if (visitor.getPossibleDivisionByZero()) {
        possibleDivisionByZero.put(unit, true);
      }

      // Set in flowed values
      out.setValue(variable.getName(), resolvedValue);
    }

    // Agrega la información de Dataflow para cada instrucción
    // Sin esto, soot no agrega de ninguna forma esta información al output.
    unit.addTag(new StringTag(out.toString()));
  }

  protected ZeroAbstractSet newInitialFlow() {
    return new ZeroAbstractSet();
  }

  protected void merge(ZeroAbstractSet input1, ZeroAbstractSet input2, ZeroAbstractSet output) {
    output.putAll(input1.union(input2));
  }

  protected void copy(ZeroAbstractSet source, ZeroAbstractSet dest) {
    dest.clear();
    dest.putAll(source);
  }

  public boolean unitIsOffending(Unit unit) {
    return possibleDivisionByZero.getOrDefault(unit, false);
  }

}
