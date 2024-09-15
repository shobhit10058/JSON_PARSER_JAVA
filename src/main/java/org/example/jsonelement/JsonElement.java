package org.example.jsonelement;

public abstract class JsonElement {
	protected Object value;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

	public abstract JsonElementType getType();

	@Override
	public abstract String toString();
}
