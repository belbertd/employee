package uk.ac.bbsrc.tgac.miso.dto;

import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import uk.ac.bbsrc.tgac.miso.core.data.ConcentrationUnit;
import uk.ac.bbsrc.tgac.miso.core.data.SampleAliquot;
import uk.ac.bbsrc.tgac.miso.core.data.SampleAliquotSingleCell;
import uk.ac.bbsrc.tgac.miso.core.data.SampleIdentity;
import uk.ac.bbsrc.tgac.miso.core.data.SampleSingleCell;
import uk.ac.bbsrc.tgac.miso.core.data.SampleSlide;
import uk.ac.bbsrc.tgac.miso.core.data.SampleStock;
import uk.ac.bbsrc.tgac.miso.core.data.SampleStockSingleCell;
import uk.ac.bbsrc.tgac.miso.core.data.SampleTissue;
import uk.ac.bbsrc.tgac.miso.core.data.SampleTissuePiece;
import uk.ac.bbsrc.tgac.miso.core.data.SampleTissueProcessing;
import uk.ac.bbsrc.tgac.miso.core.data.VolumeUnit;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @JsonSubTypes.Type(value = SampleAliquotDto.class, name = SampleAliquot.CATEGORY_NAME),
    @JsonSubTypes.Type(value = SampleAliquotSingleCellDto.class, name = SampleAliquotSingleCell.SUBCATEGORY_NAME),
    @JsonSubTypes.Type(value = SampleIdentityDto.class, name = SampleIdentity.CATEGORY_NAME),
    @JsonSubTypes.Type(value = SampleStockDto.class, name = SampleStock.CATEGORY_NAME),
    @JsonSubTypes.Type(value = SampleStockSingleCellDto.class, name = SampleStockSingleCell.SUBCATEGORY_NAME),
    @JsonSubTypes.Type(value = SampleTissueDto.class, name = SampleTissue.CATEGORY_NAME),
    @JsonSubTypes.Type(value = SampleTissueProcessingDto.class, name = SampleTissueProcessing.CATEGORY_NAME),
    @JsonSubTypes.Type(value = SampleSlideDto.class, name = SampleSlide.SUBCATEGORY_NAME),
    @JsonSubTypes.Type(value = SampleTissuePieceDto.class, name = SampleTissuePiece.SUBCATEGORY_NAME),
    @JsonSubTypes.Type(value = SampleSingleCellDto.class, name = SampleSingleCell.SUBCATEGORY_NAME),
    @JsonSubTypes.Type(value = SampleDto.class, name = "Plain") })
@JsonTypeName(value = "Plain")
public class SampleDto extends AbstractBoxableDto {

  private Long id;
  private String accession;
  private String name;
  private String description;
  // Skipped security profile
  private String identificationBarcode;
  private String locationBarcode;
  private String locationLabel;
  private String sampleType;
  private String receivedDate;
  private Boolean qcPassed;
  private String alias;
  private Long projectId;
  private String projectName;
  private String projectAlias;
  private String projectShortName;
  private String scientificName;
  private String taxonIdentifier;
  private Long rootSampleClassId;
  private String initialVolume;
  private String volume;
  @Enumerated(EnumType.STRING)
  private VolumeUnit volumeUnits;
  private String concentration;
  @Enumerated(EnumType.STRING)
  private ConcentrationUnit concentrationUnits;
  private Long updatedById;
  private String lastModified;
  private String qcDv200;
  private String qcRin;
  private List<QcDto> qcs;
  private boolean distributed;
  private String distributionDate;
  private String distributionRecipient;
  private String requisitionId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getAccession() {
    return accession;
  }

  public void setAccession(String accession) {
    this.accession = accession;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getIdentificationBarcode() {
    return identificationBarcode;
  }

  @JsonInclude(JsonInclude.Include.ALWAYS)
  public void setIdentificationBarcode(String identificationBarcode) {
    this.identificationBarcode = identificationBarcode;
  }

  public String getLocationBarcode() {
    return locationBarcode;
  }

  public void setLocationBarcode(String locationBarcode) {
    this.locationBarcode = locationBarcode;
  }

  public String getLocationLabel() {
    return locationLabel;
  }

  @JsonInclude(JsonInclude.Include.ALWAYS)
  public void setLocationLabel(String locationLabel) {
    this.locationLabel = locationLabel;
  }

  public String getSampleType() {
    return sampleType;
  }

  public void setSampleType(String sampleType) {
    this.sampleType = sampleType;
  }

  public String getReceivedDate() {
    return receivedDate;
  }

  public void setReceivedDate(String receivedDate) {
    this.receivedDate = receivedDate;
  }

  public Boolean getQcPassed() {
    return qcPassed;
  }

  @JsonInclude(JsonInclude.Include.ALWAYS)
  public void setQcPassed(Boolean qcPassed) {
    this.qcPassed = qcPassed;
  }

  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public Long getProjectId() {
    return projectId;
  }

  public void setProjectId(Long projectId) {
    this.projectId = projectId;
  }

  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public String getProjectAlias() {
    return projectAlias;
  }

  public void setProjectAlias(String projectAlias) {
    this.projectAlias = projectAlias;
  }

  public String getProjectShortName() {
    return projectShortName;
  }

  public void setProjectShortName(String projectShortName) {
    this.projectShortName = projectShortName;
  }

  public String getScientificName() {
    return scientificName;
  }

  public void setScientificName(String scientificName) {
    this.scientificName = scientificName;
  }

  public String getTaxonIdentifier() {
    return taxonIdentifier;
  }

  public void setTaxonIdentifier(String taxonIdentifier) {
    this.taxonIdentifier = taxonIdentifier;
  }

  public Long getRootSampleClassId() {
    return rootSampleClassId;
  }

  public void setRootSampleClassId(Long rootSampleClassId) {
    this.rootSampleClassId = rootSampleClassId;
  }

  public String getInitialVolume() {
    return initialVolume;
  }

  public void setInitialVolume(String initialVolume) {
    this.initialVolume = initialVolume;
  }

  public String getVolume() {
    return volume;
  }

  public void setVolume(String volume) {
    this.volume = volume;
  }

  public String getConcentration() {
    return concentration;
  }

  public void setConcentration(String concentration) {
    this.concentration = concentration;
  }

  public Long getUpdatedById() {
    return updatedById;
  }

  public void setUpdatedById(Long updatedById) {
    this.updatedById = updatedById;
  }

  public String getLastModified() {
    return lastModified;
  }

  public void setLastModified(String lastModified) {
    this.lastModified = lastModified;
  }

  public String getQcDv200() {
    return qcDv200;
  }

  public void setQcDv200(String qcDv200) {
    this.qcDv200 = qcDv200;
  }

  public String getQcRin() {
    return qcRin;
  }

  public void setQcRin(String qcRin) {
    this.qcRin = qcRin;
  }

  public List<QcDto> getQcs() {
    return qcs;
  }

  public void setQcs(List<QcDto> qcs) {
    this.qcs = qcs;
  }

  @JsonInclude(JsonInclude.Include.ALWAYS)
  public VolumeUnit getVolumeUnits() {
    return volumeUnits;
  }

  public void setVolumeUnits(VolumeUnit volumeUnits) {
    this.volumeUnits = volumeUnits;
  }

  @JsonInclude(JsonInclude.Include.ALWAYS)
  public ConcentrationUnit getConcentrationUnits() {
    return concentrationUnits;
  }

  public void setConcentrationUnits(ConcentrationUnit concentrationUnits) {
    this.concentrationUnits = concentrationUnits;
  }

  public boolean isDistributed() {
    return distributed;
  }

  public void setDistributed(boolean distributed) {
    this.distributed = distributed;
  }

  public String getDistributionDate() {
    return distributionDate;
  }

  public void setDistributionDate(String distributionDate) {
    this.distributionDate = distributionDate;
  }

  public String getDistributionRecipient() {
    return distributionRecipient;
  }

  public void setDistributionRecipient(String distributionRecipient) {
    this.distributionRecipient = distributionRecipient;
  }

  public String getRequisitionId() {
    return requisitionId;
  }

  public void setRequisitionId(String requisitionId) {
    this.requisitionId = requisitionId;
  }

}
