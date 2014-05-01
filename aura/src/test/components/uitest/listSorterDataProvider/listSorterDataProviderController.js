({
	onInit: function(cmp) {		
		cmp.getValue('v.columns').setValue(cmp.get('m.columns'));
		cmp.getValue('v.sortBy').setValue(cmp.get('m.defaultOrderByList'))
	},
	
	onProvide: function(cmp, evt, helper) {		
		helper.fireDataChangeEvent(cmp, {columns: cmp.get('m.columns'), orderBy: cmp.get('m.defaultOrderByList')});
	}
})