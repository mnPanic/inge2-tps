package dataflow.abs;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ZeroAbstractSet {
	private final HashMap<String, ZeroAbstractValue> map;

	public ZeroAbstractSet(){
		this.map = new HashMap<String, ZeroAbstractValue>();
	}

	public Boolean hasValue(String variable) {
		return this.map.containsKey(variable);
	}

	public ZeroAbstractValue getValue(String variable) {
		return this.map.get(variable);
	}

	public void setValue(String variable, ZeroAbstractValue value) {
		if (value != null) {
			this.map.put(variable, value);
		}
	}

	public ZeroAbstractSet union(ZeroAbstractSet another) {
		ZeroAbstractSet unionSet = new ZeroAbstractSet();

		// Necesitamos hacer el merge en ambos sentidos para considerar las claves de los dos conjuntos
		// Ya que podría haber una clave que esté en el primero y no en el segundo (contemplado con el primer merge)
		// o al revés (contemplado con el segundo merge).
		mergeWith(this, another, unionSet);
		mergeWith(another, this, unionSet);

		return unionSet;
	}

	public void mergeWith(ZeroAbstractSet first, ZeroAbstractSet second, ZeroAbstractSet unionSet) {
		for(Map.Entry<String, ZeroAbstractValue> entry : first.map.entrySet()) {
			String variable = entry.getKey();
			ZeroAbstractValue value = entry.getValue();

			if(second.hasValue(variable)) {
				unionSet.setValue(variable, value.merge(second.getValue(variable)));
			} else {
				unionSet.setValue(variable, value);
			}
		}
	}

	public void clear() {
		this.map.clear();
	}

	public void putAll(ZeroAbstractSet another) {
		this.map.putAll(another.map);
	}

	@Override
	public String toString() {
		return "ZeroAbstractSet{" + this.map + "}";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ZeroAbstractSet)) return false;
		ZeroAbstractSet that = (ZeroAbstractSet) o;
		return Objects.equals(this.map, that.map);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.map);
	}
}
