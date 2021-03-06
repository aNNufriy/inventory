$(document).ready(function () {
    document.dt = $('table#sample').DataTable({
        'ajax': {
            'contentType': 'application/json',
            'url': '/api/dataTables/loginUserRole',
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
                data: 'id',
                width: "40%"
            }, {
                data: 'name',
                width: "15%"
            }, {
                data: null,
                width: "20%",
                searchable: false,
                sortable: false,
                render: function ( data, type, _ ) {
                    if(type==='display'){
                        let edit = "<button class='dt-edit-btn btn-success btn' data-id="+data.id+"><b>Edit</b></button>";
                        let remove = "<button class='dt-remove-btn btn-danger btn' data-id="+data.id+"><b>Remove</b></button>";
                        return  edit + " " + remove
                    }else{
                        return type
                    }
                }
            }
        ],
        "drawCallback": function() {
            $(".dt-edit-btn").click(function(_){
                location.href = "/loginUserRole/"+this.attributes['data-id'].value+"/edit";
            });
            $(".dt-remove-btn").click(function(){
                let url = "/api/loginUserRole/"+this.attributes['data-id'].value;
                if(confirm("Do you want to remove item?")){
                    $.ajax({
                        url: url,
                        type: 'DELETE',
                        success: function(_) {
                            document.dt.draw(false)
                        }
                    });
                }
            })
        }
    })
});