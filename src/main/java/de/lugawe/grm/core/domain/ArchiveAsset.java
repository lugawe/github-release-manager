package de.lugawe.grm.core.domain;

public final class ArchiveAsset {

    private String path;
    private String name;
    private byte[] content;

    public ArchiveAsset() {}

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

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
