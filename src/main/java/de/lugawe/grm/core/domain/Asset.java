package de.lugawe.grm.core.domain;

public final class Asset {

    private String name;
    private byte[] content;

    public Asset() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
