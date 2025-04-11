function addlocation(){
	try{
		var obj = {};
		obj['city'] = $('#city').val();
		obj['zipcode'] = $('#zipcode').val();
		obj['country'] = $('#country').val();
		obj['county'] = $('#county').val();
		obj['state'] = $('#state').val();
		
		$.ajax({
			type: 'POST',
			url: '/location/add',
			data: JSON.stringify( obj),
			contentType: 'application/json',
			dataType: 'json',
			success: function(data){
				
			},
	        error: function(xhr, ajaxOptions, thrownError, request, error){
	        	console.log('xrs.status = ' + xhr.status + '\n' + 
	        	            'thrown error = ' + thrownError + '\n' +
	        	            'xhr.statusText = '  + xhr.statusText + '\n' +
	        	            'request = ' + request + '\n' +
	        	            'error = ' + error);
	        }
		}).then(function(output){
			console.log("response "+output);
			$.each(
					output,
					function(index,value){
							alert(value);
							return false;
					}	
			);
		});
	}catch(err){
		alert(err.message);
	}
}
function addhome(){
	try{
		var obj = {};
		obj['zipcode'] = $('#homezipcode').val();
		obj['description'] = $('#homedescription').val();
		obj['streetAddress'] = $('#homestreetaddress').val();
		obj['type'] = $('#hometype').val();
		obj['price'] = $('#homeprice').val();
		obj['area'] = $('#homearea').val();
		$.ajax({
			type: 'POST',
			url: '/property/home/add',
			data: JSON.stringify( obj),
			contentType: 'application/json',
			dataType: 'json',
			success: function(data){
				
			},
	        error: function(xhr, ajaxOptions, thrownError, request, error){
	        	
	        	console.log('xrs.status = ' + xhr.status + '\n' + 
	        	            'thrown error = ' + thrownError + '\n' +
	        	            'xhr.statusText = '  + xhr.statusText + '\n' +
	        	            'request = ' + request + '\n' +
	        	            'error = ' + error);
	        }
		}).then(function(output){
			console.log("response "+output);
			$.each(
					output,
					function(index,value){
							alert(value);
							return false;
					}	
			);
		});
	}catch(err){
		alert(err.message);
	}
}
function getHomes(){
	
}
function getLocations(){
	console.log("getLocations called");
	try{
		var url = '/location/fetchall';
		$.ajax({
			type: 'GET',
			url: '/location/fetchall',
			contentType: "application/json;charset=utf-8",
	        dataType: "json",
			success: function(data){
				
			},
	        error: function(e){
	        	console.log(e.responseText);
	        }
		}).then(function(output){
			console.log(output);
			$.each(
					output,
					function(index,value){
						
							var tableColumns = value.tablecolumns;
							var tableContent = value.tablecontent;
							console.log('tableContent'+tableContent);
							$('#table_alllocations').DataTable( {
								paging: false,
								searching: false,
							    fixedColumns:   {
							         left: 2
							    },
								columnDefs: [ {
						            orderable: false,
						            className: 'select-checkbox',
						            targets:   0
						        } ],
						        dom: 'Bfrtip',
						        //select: true,
						        select: {
						            style:    'os',
						            selector: 'td:first-child'
						        },
						        order: [[ 0, 'asc' ]],
							    "data": tableContent,
						        "columns": tableColumns
						    } );
							return false;
						
					}	
			);
			
		});
	}catch(err){
		alert(err.message);
	}
}

function populatehomelocations(zipcode){
	try{
		console.log("zc = "+zipcode);
		var resturl = '/location/zipcode/'+zipcode;
		$.ajax({
			type: 'GET',
			url: resturl,
			contentType: "application/json;charset=utf-8",
	        dataType: "json",
			success: function(data){
				
			},
	        error: function(e){
	        	console.log(e.responseText);
	        }
		}).then(function(output){
			console.log(output);
			$.each(
					output,
					function(index,value){
						if(index == 'homecity'){
							$('#homecity').val(value);
						}else if(index == 'homecounty'){
							$('#homecounty').val(value);
						}else if(index == 'homestate'){
							$('#homestate').val(value);
						}else if(index == 'homecountry'){
							$('#homecountry').val(value);
						}
					}
			);
			
		});
	}catch(err){
		alert(err.message);
	}
}
function suggestions(event,id){
	console.log("suggestion called");
		var p = '#'+id;
		actions = getSuggestions(id);
		console.log('p = '+p);
		console.log('actions = '+actions);
		$( p ).autocomplete({
			autoFocus: true,
			source: actions,
			select: function( event, ui ) {
				if(id == 'homezipcode'){
					populatehomelocations(ui.item.label);
				}
			},
			classes: {
			    "ui-autocomplete": "highlight"
			}
		});
}


function getSuggestions(id){
	var a = getCookie(id);
	if(a == null || '' == a.trim()){
		initialize();
		a = getCookie(id);
	}
	console.log(a);
	var j = JSON.parse(a);
	console.log('id = '+id);
	if('homezipcode' == id){
		return j.zipcodes;
	}
}

function initialize(){
	$.ajax({
		type: 'GET',
		url: '/location/zipcodes',
		contentType: "application/json;charset=utf-8",
        dataType: "json",
		success: function(data){
			
		},
		async: false,
        error: function(e){
        	console.log(e.responseText);
        }
	}).then(function(data){
		const c = JSON.stringify(data);
		console.log(data);
		document.cookie = 'homezipcode'+"="+c;
	});
}
function getCookie(cname) {
	  let name = cname + "=";
	  let decodedCookie = decodeURIComponent(document.cookie);
	  console.log('searching for '+cname);
	  //console.log('decodedCookie is '+decodedCookie);
	  let ca = decodedCookie.split(';');
	  for(let i = 0; i <ca.length; i++) {
	    let c = ca[i];
	    while (c.charAt(0) == ' ') {
	      c = c.substring(1);
	    }
	    if (c.indexOf(name) == 0) {
	      return c.substring(name.length, c.length);
	    }
	  }
	  return "";
	}
