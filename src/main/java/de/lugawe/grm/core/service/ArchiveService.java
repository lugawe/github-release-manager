package de.lugawe.grm.core.service;

import java.io.InputStream;
import java.util.List;

import de.lugawe.grm.core.domain.ArchiveAsset;

public interface ArchiveService {

    List<ArchiveAsset> extract(InputStream inputStream);
}
