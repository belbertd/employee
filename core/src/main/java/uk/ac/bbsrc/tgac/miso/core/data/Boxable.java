package uk.ac.bbsrc.tgac.miso.core.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.function.Supplier;

import com.eaglegenomics.simlims.core.User;

import uk.ac.bbsrc.tgac.miso.core.data.impl.LibraryAliquot;
import uk.ac.bbsrc.tgac.miso.core.data.impl.LibraryImpl;
import uk.ac.bbsrc.tgac.miso.core.data.impl.PoolImpl;
import uk.ac.bbsrc.tgac.miso.core.data.impl.SampleImpl;
import uk.ac.bbsrc.tgac.miso.core.data.impl.boxposition.LibraryAliquotBoxPosition;
import uk.ac.bbsrc.tgac.miso.core.data.impl.boxposition.LibraryBoxPosition;
import uk.ac.bbsrc.tgac.miso.core.data.impl.boxposition.PoolBoxPosition;
import uk.ac.bbsrc.tgac.miso.core.data.impl.boxposition.SampleBoxPosition;

/**
 * This interface simply describes an object that can be placed into a box. i.e. Sample, Library
 * 
 */
public interface Boxable extends Nameable, Barcodable, Distributable, Serializable {

  public enum EntityType {
    SAMPLE(SampleImpl.class, SampleBoxPosition::new),
    LIBRARY(LibraryImpl.class, LibraryBoxPosition::new),
    LIBRARY_ALIQUOT(LibraryAliquot.class, LibraryAliquotBoxPosition::new),
    POOL(PoolImpl.class, PoolBoxPosition::new);

    private final Class<? extends Boxable> persistClass;
    private final Supplier<? extends AbstractBoxPosition> positionConstructor;

    private EntityType(Class<? extends Boxable> persistClass, Supplier<? extends AbstractBoxPosition> positionConstructor) {
      this.persistClass = persistClass;
      this.positionConstructor = positionConstructor;
    }

    public Class<? extends Boxable> getPersistClass() {
      return persistClass;
    }

    public AbstractBoxPosition makeBoxPosition() {
      return positionConstructor.get();
    }
  }

  public EntityType getEntityType();

  /**
   * Returns the alias of this Sample object.
   *
   * @return String alias.
   */
  @Override
  public String getAlias();

  public Box getBox();

  public String getBoxPosition();

  public Date getLastModified();

  public String getLocationBarcode();

  /**
   * Returns the volume of the Implementor
   * 
   * @return volume
   */
  public BigDecimal getVolume();

  /**
   * Returns whether or not the Implementor has been emptied
   * 
   * @return emptied
   */
  public boolean isDiscarded();

  /**
   * Sets the alias of this Sample object.
   *
   * @param alias
   *          alias.
   */
  public void setAlias(String alias);

  /**
   * Sets the 'emptied' attribute for the Implementor
   * 
   * @param boolean
   *          emptied
   */
  public void setDiscarded(boolean emptied);

  /**
   * Sets the volume of the Implementor
   * 
   * @param volume
   */
  public void setVolume(BigDecimal volume);

  public Long getPreMigrationId();

  public void setLastModified(Date lastModified);

  public void setLastModifier(User user);

  /**
   * Remove Box and position information from this Boxable
   */
  public void removeFromBox();

}
