package uk.ac.bbsrc.tgac.miso.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uk.ac.bbsrc.tgac.miso.core.data.Partition;
import uk.ac.bbsrc.tgac.miso.core.data.PartitionQC;
import uk.ac.bbsrc.tgac.miso.core.data.Run;
import uk.ac.bbsrc.tgac.miso.core.service.ContainerService;
import uk.ac.bbsrc.tgac.miso.core.service.PartitionQCService;
import uk.ac.bbsrc.tgac.miso.core.service.PartitionQcTypeService;
import uk.ac.bbsrc.tgac.miso.core.service.RunService;
import uk.ac.bbsrc.tgac.miso.persistence.PartitionQcStore;

@Service
@Transactional(rollbackFor = Exception.class)
public class DefaultPartitionQCService implements PartitionQCService {

  @Autowired
  private ContainerService containerService;
  @Autowired
  private PartitionQcStore partitionQcDao;
  @Autowired
  private PartitionQcTypeService partitionQcTypeService;
  @Autowired
  private RunService runService;

  @Override
  public PartitionQC get(Run run, Partition partition) throws IOException {
    Run managedRun = runService.get(run.getId());
    return partitionQcDao.get(managedRun, partition);
  }

  @Override
  public void save(PartitionQC qc) throws IOException {
    PartitionQC managedQc = get(qc.getRun(), qc.getPartition());
    Run managedRun = runService.get(qc.getRun().getId());
    Partition managedPartition = containerService.getPartition(qc.getPartition().getId());

    // update run and container for accurate lastModified and lastModifier (used in changelogs)
    runService.update(managedRun);
    containerService.update(managedPartition.getSequencerPartitionContainer());

    if (managedQc == null) {
      qc.setRun(managedRun);
      qc.setPartition(managedPartition);
      qc.setType(partitionQcTypeService.get(qc.getType().getId()));
      partitionQcDao.create(qc);
    } else {
      managedQc.setType(partitionQcTypeService.get(qc.getType().getId()));
      managedQc.setNotes(qc.getNotes());
      partitionQcDao.update(managedQc);
    }
  }
}
