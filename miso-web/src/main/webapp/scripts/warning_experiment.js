/*
 * Copyright (c) 2012. The Genome Analysis Centre, Norwich, UK
 * MISO project contacts: Robert Davey @ TGAC
 * *********************************************************************
 *
 * This file is part of MISO.
 *
 * MISO is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MISO is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MISO.  If not, see <http://www.gnu.org/licenses/>.
 *
 * *********************************************************************
 */

WarningTarget.experiment_run_partition = {
  getWarnings: function(data) {
    var pool = data.partition.pool;
    return [{
      include: pool.prioritySubprojectAliases && pool.prioritySubprojectAliases.length > 0,
      tableMessage: 'PRIORITY (' + (pool.prioritySubprojectAliases.length == 1 ? pool.prioritySubprojectAliases[0] : 'MULTIPLE') + ')',
      level: 'important'
    }, {
      include: pool.duplicateIndices,
      tableMessage: Constants.warningMessages.duplicateIndices
    }, {
      include: pool.nearDuplicateIndices && !pool.duplicateIndices,
      tableMessage: Constants.warningMessages.nearDuplicateIndices
    }, {
      include: pool.hasEmptySequence,
      tableMessage: Constants.warningMessages.missingIndex
    }, {
      include: pool.hasLowQualityLibraries,
      tableMessage: Constants.warningMessages.lowQualityLibraries
    }, {
      include: pool.pooledElements && pool.pooledElements.some(function(element) {
        return element.identityConsentLevel === 'Revoked';
      }),
      tableMessage: Constants.warningMessages.consentRevoked
    }];
  }
};
