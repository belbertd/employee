HotTarget.subproject = {
  getCreateUrl: function() {
    return Urls.rest.subprojects.create;
  },
  getUpdateUrl: function(id) {
    return Urls.rest.subprojects.update(id);
  },
  requestConfiguration: function(config, callback) {
    callback(config)
  },
  fixUp: function(subproject, errorHandler) {
  },
  createColumns: function(config, create, data) {
    return [
        HotUtils.makeColumnForConstantsList('Project', create, 'projectAlias', 'parentProjectId', 'id', Constants.isDetailedSample
            ? 'shortName' : 'alias', config.projects, true, {}, null),

        HotUtils.makeColumnForText('Alias', true, 'alias', {
          unpackAfterSave: true,
          validator: HotUtils.validator.requiredText
        }),

        HotUtils.makeColumnForText('Description', true, 'description', {
          unpackAfterSave: true,
        }),
        HotUtils.makeColumnForBoolean('Priority', true, 'priority', true),

        HotUtils.makeColumnForConstantsList('Reference Genome', true, 'referenceGenomeAlias', 'referenceGenomeId', 'id', 'alias',
            Constants.referenceGenomes, true, {}, null), ];
  },

  getBulkActions: function(config) {
    return !config.isAdmin ? [] : [{
      name: 'Edit',
      action: function(items) {
        window.location = window.location.origin + '/miso/subproject/bulk/edit?' + jQuery.param({
          ids: items.map(Utils.array.getId).join(',')
        });
      }
    },

    {
      name: 'Delete',
      action: function(items) {
        var deleteNext = function(index) {
          if (index == items.length) {
            window.location = window.location.origin + '/miso/subproject/list';
          }
          Utils.ajaxWithDialog('Deleting ' + items[index].alias, 'DELETE', '/miso/rest/subprojects/' + items[index].id, null, function() {
            deleteNext(index + 1);
          });
        };
        deleteNext(0);
      }
    }, ];
  }
};
