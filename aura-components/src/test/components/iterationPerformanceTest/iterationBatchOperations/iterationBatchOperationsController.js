({
    setup: function (cmp) {
        var tenValues = [
                         { value : " Value01 " }, 
                         { value : " Value02 " },
                         { value : " Value03 " },
                         { value : " Value04 " },
                         { value : " Value05 " },
                         { value : " Value06 " }, 
                         { value : " Value07 " },
                         { value : " Value08 " },
                         { value : " Value09 " },
                         { value : " Value10 " }
                     ];
        cmp.set('v.initialState', {
            initialCmpCount : $A.componentService.countComponents(),
            dataSetSize     : tenValues.length
        });
    },
    run: function (cmp, event) {
        var tenValues = [
                         { value : " Value01 " }, 
                         { value : " Value02 " },
                         { value : " Value03 " },
                         { value : " Value04 " },
                         { value : " Value05 " },
                         { value : " Value06 " }, 
                         { value : " Value07 " },
                         { value : " Value08 " },
                         { value : " Value09 " },
                         { value : " Value10 " }
                     ];
        var testData      = tenValues.slice(0);
        var testDataBatch = $A.util.map(testData, function (i) {
            return $A.util.copy(i);
        });

        cmp.set('v.iterationItems', testData);
        cmp.set('v.iterationItems', testDataBatch);
    },
    postProcessing: function (cmp, event) {
        var dataSetSize = cmp.get('v.initialState.dataSetSize');
        var results     = event.getParam('arguments').results;
        var common      = results.commonMetrics;
        var deltaCmps   = common.finalComponentCount - common.initialComponentCount;

        results.customMetrics.leakedComponents = deltaCmps - dataSetSize;
        console.log('Leaked cmps: ', results.customMetrics.leakedComponents);
    }
})