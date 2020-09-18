package com.z.designpatterns.structure.bridge;

public class RefinedAbstraction extends Abstraction {
    public RefinedAbstraction(Implementor implementor) {
        super(implementor);
    }

    @Override
    public void operation() {
        super.getImplementor().operationImp();
    }
}
