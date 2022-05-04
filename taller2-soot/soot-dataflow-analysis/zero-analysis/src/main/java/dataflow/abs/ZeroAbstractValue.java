package dataflow.abs;

public enum ZeroAbstractValue {

  BOTTOM("bottom"), NOT_ZERO("not-zero"), ZERO("zero"), MAYBE_ZERO("maybe-zero");

  private String name;

  @Override
  public String toString() {
    return this.name;
  }

  ZeroAbstractValue(String name) {
    this.name = name;
  }

  public ZeroAbstractValue add(ZeroAbstractValue another) {
    if(this.equals(BOTTOM) || another.equals(BOTTOM)) { return BOTTOM; }
    if(this.equals(MAYBE_ZERO) || another.equals(MAYBE_ZERO)) { return MAYBE_ZERO; }
    if(this.equals(ZERO)) { return another; }
    if(this.equals(NOT_ZERO) && another.equals(ZERO)) { return NOT_ZERO; }

    // this == NOT_ZERO && another == NZ
    return MAYBE_ZERO;
  }

  public ZeroAbstractValue divideBy(ZeroAbstractValue another) {
    if(this.equals(BOTTOM) || another.equals(BOTTOM)) { return BOTTOM; }
    if(another.equals(ZERO)) { return BOTTOM; }

    // | IN[n](y) | IN[n](z) | OUT[n](x) |
    // |:--------:|:--------:|:---------:|
    // | `Z`      | `NZ`     | `Z`       |
    // | `NZ`     | `NZ`     | `NZ`      |
    // | `MZ`     | `NZ`     | `MZ`      |
    // | `Z`      | `MZ`     | `Z`       |
    // | `NZ`     | `MZ`     | `NZ`      |
    // | `MZ`     | `MZ`     | `MZ`      |
    return this;
  }

  public ZeroAbstractValue multiplyBy(ZeroAbstractValue another) {
    if(this.equals(BOTTOM) || another.equals(BOTTOM)) { return BOTTOM; }
    if(this.equals(ZERO) || another.equals(ZERO)) { return ZERO; }
    if(this.equals(MAYBE_ZERO) || another.equals(MAYBE_ZERO)) { return MAYBE_ZERO; }

    // this == NZ && another == NZ
    return NOT_ZERO;
  }

  public ZeroAbstractValue substract(ZeroAbstractValue another) {
    // "trampa" para simplificar
    return this.add(another);
  }

  public ZeroAbstractValue merge(ZeroAbstractValue another) {
    if(this.equals(another)) { return this; }
    if(this.equals(MAYBE_ZERO) || another.equals(MAYBE_ZERO)) { return MAYBE_ZERO; }
    if(this.equals(BOTTOM)) { return another; }
    if(another.equals(BOTTOM)) { return this; }

    return MAYBE_ZERO;
  }
  
}
