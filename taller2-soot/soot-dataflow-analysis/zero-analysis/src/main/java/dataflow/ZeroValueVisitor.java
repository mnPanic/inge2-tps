package dataflow;

import java.util.Map;

import dataflow.abs.ZeroAbstractValue;
import dataflow.abs.ZeroAbstractSet;
import dataflow.utils.AbstractValueVisitor;
import dataflow.utils.ValueVisitor;
import soot.jimple.ParameterRef;
import soot.Local;
import soot.Value;

public class ZeroValueVisitor extends AbstractValueVisitor<ZeroAbstractValue> {

  private final ZeroAbstractSet set;
  private ZeroAbstractValue resolvedValue;
  private Boolean possibleDivisionByZero;

  public ZeroValueVisitor(ZeroAbstractSet set) {
    this.set = set;
    this.possibleDivisionByZero = false;
  }

  @Override
  protected void visitParameterRef(ParameterRef parameter) {
    resolvedValue = ZeroAbstractValue.MAYBE_ZERO;
  }

  @Override
  public void visitLocal(Local variable) {
    String name = variable.getName();
    if (set.hasValue(name)){
      resolvedValue = set.getValue(name);
    } else{
      resolvedValue = ZeroAbstractValue.ZERO;
    }
  }

  @Override
  public void visitDivExpression(ZeroAbstractValue leftOperand, ZeroAbstractValue rightOperand) {
    // Si el divisor tal vez es zero (maybe zero), el análisis continúa pero de todas formas
    // queremos arrojar un warning.
    if (rightOperand.equals(ZeroAbstractValue.MAYBE_ZERO) || rightOperand.equals(ZeroAbstractValue.ZERO)) {
      possibleDivisionByZero = true;
    }

    resolvedValue = leftOperand.divideBy(rightOperand);
  }
  

  @Override
  public void visitMulExpression(ZeroAbstractValue leftOperand, ZeroAbstractValue rightOperand) {
    resolvedValue = leftOperand.multiplyBy(rightOperand);
  }

  @Override
  public void visitSubExpression(ZeroAbstractValue leftOperand, ZeroAbstractValue rightOperand) {
    resolvedValue = leftOperand.substract(rightOperand);
  }

  @Override
  public void visitAddExpression(ZeroAbstractValue leftOperand, ZeroAbstractValue rightOperand) {
    resolvedValue = leftOperand.add(rightOperand);
  }

  @Override
  public void visitIntegerConstant(int value) {
    if (value == 0) {
      this.resolvedValue = ZeroAbstractValue.ZERO;
    } else {
      this.resolvedValue = ZeroAbstractValue.NOT_ZERO;
    }
  }

  @Override
  public ZeroAbstractValue done() {
    return resolvedValue;
  }

  @Override
  public ValueVisitor cloneVisitor() {
    return new ZeroValueVisitor(set);
  }

  public Boolean getPossibleDivisionByZero() {
    return possibleDivisionByZero;
  }
}
