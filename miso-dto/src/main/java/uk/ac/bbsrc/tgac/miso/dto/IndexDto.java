package uk.ac.bbsrc.tgac.miso.dto;

import java.util.List;

public class IndexDto {
  private IndexFamilyDto family;
  private long id;
  private String label;
  private String name;
  private int position = 1;
  private String sequence;
  private List<String> realSequences;

  public IndexFamilyDto getFamily() {
    return family;
  }

  public long getId() {
    return id;
  }

  public String getLabel() {
    return label;
  }

  public String getName() {
    return name;
  }

  public int getPosition() {
    return position;
  }

  public String getSequence() {
    return sequence;
  }

  public void setFamily(IndexFamilyDto family) {
    this.family = family;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPosition(int position) {
    this.position = position;
  }

  public void setSequence(String sequence) {
    this.sequence = sequence;
  }

  public List<String> getRealSequences() {
    return realSequences;
  }

  public void setRealSequences(List<String> realSequences) {
    this.realSequences = realSequences;
  }
}
