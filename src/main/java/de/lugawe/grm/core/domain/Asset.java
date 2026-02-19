package de.lugawe.grm.core.domain;

import java.util.List;

public final class Asset {

    private String name;
    private String contentType;
    private String url;

    private byte[] content;

    private List<ArchiveAsset> archiveAssets;

    public Asset() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public List<ArchiveAsset> getArchiveAssets() {
        return archiveAssets;
    }

    public void setArchiveAssets(List<ArchiveAsset> archiveAssets) {
        this.archiveAssets = archiveAssets;
    }
}
