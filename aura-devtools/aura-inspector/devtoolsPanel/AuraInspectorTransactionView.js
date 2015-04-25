function AuraInspectorTransactionView(devtoolsPanel) {
	var initialLoad = false;
	var transactions = {};
	this.init = function() {
		console.log('initializing AuraInspectorTransaction');
	};
	this.setContainer = function () {
		this.container = document.getElementById('trs');
	};

	this.render = function() {
		if (!initialLoad) {
	        initialLoad = true;
	        this.setContainer();
	        this.bind();
	        this.container.addEventListener('click', this._onTransactionClick.bind(this), false);
		}
	};

	this.bind = function () {
		//this.recordButton = document.querySelector('.trans-panel .record-profile-status-bar-item');
        this.clearButton = document.querySelector('.trans-panel .clear-status-bar-item');
        this.clearButton.addEventListener('click', this._clearTable.bind(this), false);
	};

	this._onTransactionClick = function (e) {
		var id = e.target.dataset.id;
		var command = "console.log(" + JSON.stringify(transactions[id]) + ")";
        chrome.devtools.inspectedWindow.eval(command, function (payload, exception) {
            if (exception) {
            	console.log('ERROR, CMD:', command, exception);
            }
        });
	};

	this.update = function (t) {
		this.addRow(t);
	};

	this._clearTable = function (e) {
		var container = this.container;
		var tbody = container.querySelector('tbody');

		while (tbody.firstChild) {
    		tbody.removeChild(tbody.firstChild);
		}
	};

	this.summarizeActions = function (t) {
		return (t.marks.serverActions.filter(function (m) {
			return m.phase === 'stamp';
		})).reduce(function (r, m) {
			return m.context.ids.length + r;
		}, 0);
	};
	this.summarizeXHR = function (t) {
		var transportMarks = t.marks.transport;
		var counter = 0;
        var queue = {};
        for (var i = 0; i < transportMarks.length; i++) {
            var id = transportMarks[i].context["aura.num"];
            var phase = transportMarks[i].phase;
            if (phase === 'processed') {
                ++counter;
            } else if (phase === 'start') {
                queue[id] = transportMarks[i];
            } else if (phase === 'end' && queue[id]){
                ++counter;
                delete queue[id];
            }
        }
        return counter;
	};

	this.contextualizeTime = function (t) {
		return Math.floor(t.ts / 10) / 100;
	};

	this.addRow = function (t) {
		var container = this.container;
		var tbody = container.querySelector('tbody');
		var tr = document.createElement('tr');
		var tid = t.id + ':' + Math.floor(t.ts);

		transactions[tid] = t;
		// <th>Id</th>
		// <th>StartTime</th>
		// <th>Duration</th>
		// <th>Context</th>
		// <th>Marks</th>
		tr.innerHTML = [
			'<td class="id"><a href="javascript:void(0)" data-id="'+ tid +'">' + t.id + '</a></td>',
			'<td class="ts">' + this.contextualizeTime(t) +'</td>',
			'<td class="dur">' + Math.floor(t.duration * 1000) / 1000 +'</td>',
			'<td class="ctx">' + JSON.stringify(t.context, null, '\t') + '</td>',
			'<td class="actions">' + this.summarizeActions(t) + '</td>',
			'<td class="xhr">' + this.summarizeXHR(t) + '</td>',
		].join('');

		tbody.appendChild(tr);
	};
}