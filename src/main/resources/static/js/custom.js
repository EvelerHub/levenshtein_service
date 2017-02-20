$('#add_button').click(function () {
    $('#pairs').append(
        '<div class="row margin-bottom-30">' +
        '<div class="col-md-6">' +
        '<input class="form-control left-value"/>'+
        '</div>'+
        '<div class="col-md-6">'+
        '<input class="form-control right-value"/>'+
        '</div>'+
        '</div>'
    );
});

$('#upload_input').change(function () {
    var form_data = new FormData();
    form_data.append("file", $('#upload_input')[0].files[0]);

    $.ajax({
        url: '/levenshtein/uploadcsv',
        type: 'POST',
        processData: false,
        contentType: false,
        data: form_data,
        beforeSend: function () {
            $('#upload_progress').show();
        },
        error: function () {
            $("#modal_error .modal-body").html(
                '<p>Был отправлен некоректный файл! Файл должен быть оформлен как этот ' +
                '<a href="/example.csv">example.csv</a></p>'
            );
            $('#modal_error').modal('show');
        },
        success: function(data, textStatus, request) {
            var blob = new Blob([data], {type: request.getResponseHeader('Content-Type')});
            var downloadUrl = URL.createObjectURL(blob);
            var a = document.createElement("a");
            a.href = downloadUrl;
            a.download = request.getResponseHeader('Content-Disposition');
            document.body.appendChild(a);
            a.click();
        },
        complete: function () {
            $('#upload_progress').hide();
        }
    });
});

$('#delete_button').click(function () {
    $('#pairs .row').last().remove();
});

$('#compare_button').click(function () {
    var pairs = getPairs();
    console.log('request ==> ' + JSON.stringify(pairs));
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        dataType: 'json',
        url: '/levenshtein/compare',
        data: JSON.stringify(pairs),
        beforeSend: function () {
            $('#compared_pairs').empty();
            $('#compare_progress').show();
        },
        success: function (compared_pairs) {
            console.log('response ==> ' + JSON.stringify(compared_pairs));
            setComparedPairs(compared_pairs);
        },
        complete: function () {
            $('#compare_progress').hide();
        }
    });
});

function getPairs() {
    var data = {
        'leftIdentificators' : [],
        'rightIdentificators' : []
    };

    $('#pairs .row').each(function () {
        var left = $(this).find('.left-value').val();
        var right = $(this).find('.right-value').val();
        data.leftIdentificators.push(left);
        data.rightIdentificators.push(right);
    });

    return data;
}

function setComparedPairs(data) {
    data.forEach(function (element) {
        $('#compared_pairs').append(
            '<div class="row margin-bottom-30">' +
            '<div class="col-md-6">' +
            '<input class="form-control" value="' + element.leftIdentificator + '" disabled="disabled"/>' +
            '</div>'+
            '<div class="col-md-6">'+
            '<input class="form-control" value="' + element.rightIdentificator + '" disabled="disabled"/>' +
            '</div>'+
            '</div>'
        );
    })
}