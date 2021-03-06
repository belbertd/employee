ListTarget.arraymodel = {
  name: "Array Models",
  createUrl: function(config, projectId) {
    throw new Error("Must be provided statically");
  },
  getQueryUrl: null,
  createBulkActions: function(config, projectId) {
    var actions = HotTarget.arraymodel.getBulkActions(config);
    if (config.isAdmin) {
      actions.push(ListUtils.createBulkDeleteAction('Array Models', 'arraymodels', Utils.array.getAlias));
    }
    return actions;
  },
  createStaticActions: function(config, projectId) {
    return config.isAdmin ? [ListUtils.createStaticAddAction('Array Models', 'arraymodel')] : [];
  },
  createColumns: function(config, projectId) {
    return [{
      sTitle: 'Alias',
      mData: 'alias',
      include: true,
      iSortPriority: 1
    }, {
      sTitle: 'Rows',
      mData: 'rows',
      include: true,
      iSortPriority: 0
    }, {
      sTitle: 'Columns',
      mData: 'columns',
      include: true,
      iSortPriority: 0
    }];
  }
};
