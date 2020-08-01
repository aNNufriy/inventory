$(document).ready(function () {
    document.dt = $('table#sample').DataTable({
        'ajax': {
            'contentType': 'application/json',
            'url': '/api/dataTables/loginUsers',
            'type': 'POST',
            'data': function (d) {
                return JSON.stringify(d);
            }
        },
        "order": [[ 1, "asc" ]],
        "paging": true,
        'serverSide': true,
        "processing": true,
        columns: [
            {
                data: 'uuid',
                width: "40%"
            }, {
                data: 'login',
                width: "40%"
            },
            {
                width: "20%",
                "searchable":false,
                "sortable":false,
                "data":null,
                render: function ( data, type, row ) {
                    if(type==='display'){
                        let edit = "<button class='dt-edit-btn btn-success btn' data-uuid="+data.uuid+"><b>Edit</b></button>"
                        let remove = "<button class='dt-remove-btn btn-danger btn' data-uuid="+data.uuid+"><b>Remove</b></button>"
                        return  edit + " " + remove
                    }else{
                        return ''
                    }
                }
            }
        ],
        "drawCallback": function( settings ) {
            $(".dt-edit-btn").click(function(){
                location.href = "/loginUsers/"+this.attributes['data-uuid'].value+"/edit";
            })
            $(".dt-remove-btn").click(function(){
                let url = "/api/loginUsers/"+this.attributes['data-uuid'].value;
                if(confirm("Do you want to remove item?")){
                    $.ajax({
                        url: url,
                        type: 'DELETE',
                        success: function(result) {
                            document.dt.draw(false)
                        }
                    });
                }

            })
        }
    })
});