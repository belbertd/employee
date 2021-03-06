package uk.ac.bbsrc.tgac.miso.core.data.spreadsheet;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import uk.ac.bbsrc.tgac.miso.core.data.DetailedSample;
import uk.ac.bbsrc.tgac.miso.core.data.Index;
import uk.ac.bbsrc.tgac.miso.core.data.Sample;
import uk.ac.bbsrc.tgac.miso.core.data.SampleIdentity;
import uk.ac.bbsrc.tgac.miso.core.data.SampleTissue;
import uk.ac.bbsrc.tgac.miso.core.data.impl.LibraryAliquot;
import uk.ac.bbsrc.tgac.miso.core.util.BoxUtils;
import uk.ac.bbsrc.tgac.miso.core.util.LimsUtils;

public enum LibraryAliquotSpreadSheets implements Spreadsheet<LibraryAliquot> {
  TRACKING_LIST("Tracking List", //
      Column.forString("Name", LibraryAliquot::getName), //
      Column.forString("Barcode", LibraryAliquot::getIdentificationBarcode), //
      Column.forString("Library Name", libraryAliquot -> libraryAliquot.getLibrary().getName()), //
      Column.forString("Library Alias", libraryAliquot -> libraryAliquot.getLibrary().getAlias()), //
      Column.forString("Library Barcode", libraryAliquot -> libraryAliquot.getLibrary().getIdentificationBarcode()), //
      Column.forString("Library Type", libraryAliquot -> libraryAliquot.getLibrary().getLibraryType().getDescription()), //
      Column.forString("Index(es)", LibraryAliquotSpreadSheets::listIndices), //
      Column.forString("i7 Index", listIndex(1)), //
      Column.forString("i5 Index", listIndex(2)), //
      Column.forString("Targeted Sequencing",
          libraryAliquot -> libraryAliquot.getTargetedSequencing() != null ? libraryAliquot.getTargetedSequencing().getAlias() : ""), //
      Column.forString("Sample Name", libraryAliquot -> libraryAliquot.getLibrary().getSample().getName()), //
      Column.forString("Sample Alias", libraryAliquot -> libraryAliquot.getLibrary().getSample().getAlias()), //
      Column.forString("Sample Barcode", libraryAliquot -> libraryAliquot.getLibrary().getSample().getIdentificationBarcode()), //
      Column.forString("Identity Name", detailedSample(SampleIdentity.class, SampleIdentity::getName, "")), //
      Column.forString("Identity Alias", detailedSample(SampleIdentity.class, SampleIdentity::getAlias, "")), //
      Column.forString("External Identifier", detailedSample(SampleIdentity.class, SampleIdentity::getExternalName, "")), //
      Column.forString("Secondary Identifier", detailedSample(SampleTissue.class, SampleTissue::getSecondaryIdentifier, "")), //
      Column.forString("Location", BoxUtils::makeLocationLabel));

  private static <S extends DetailedSample, T> Function<LibraryAliquot, T> detailedSample(Class<S> clazz, Function<S, T> function,
      T defaultValue) {
    return aliquot -> {
      Sample sample = aliquot.getLibrary().getSample();
      if (clazz.isInstance(sample)) {
        return function.apply(clazz.cast(sample));
      }
      if (LimsUtils.isDetailedSample(sample)) {
        S parent = LimsUtils.getParent(clazz, (DetailedSample) sample);
        if (parent != null) {
          return function.apply(parent);
        }
      }
      return defaultValue;
    };
  }

  private static String listIndices(LibraryAliquot libraryAliquot) {
    return libraryAliquot.getLibrary().getIndices().stream().sorted(Comparator.comparingInt(Index::getPosition)).map(Index::getSequence)
        .collect(Collectors.joining(", "));
  }

  private static Function<LibraryAliquot, String> listIndex(int position) {
    return libraryAliquot -> libraryAliquot.getLibrary().getIndices().stream().filter(i -> i.getPosition() == position)
        .map(Index::getSequence)
        .findFirst().orElse("");
  }
  private final List<Column<LibraryAliquot>> columns;
  private final String description;

  @SafeVarargs
  private LibraryAliquotSpreadSheets(String description, Column<LibraryAliquot>... columns) {
    this.description = description;
    this.columns = Arrays.asList(columns);
  }

  @Override
  public List<Column<LibraryAliquot>> columns() {
    return columns;
  }

  @Override
  public String description() {
    return description;
  }
}
