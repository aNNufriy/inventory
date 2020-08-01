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
        "paging": true,
        'serverSide': true,
        "processing": true,
        columns: [
            {
                data: 'uuid'
            }, {
                data: 'login'
            },
            {
                "searchable":false,
                "sortable":false,
                "data":null,
                "defaultContent":
                    "<a class='dt-edit-btn btn'><b>Edit</b></a> <a class='dt-remove-btn btn'><b>Remove</b></a>"
            }
        ]
    })
});