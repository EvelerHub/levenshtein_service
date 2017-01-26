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
            $('.progress').show();
        },
        success: function (compared_pairs) {
            console.log('response ==> ' + JSON.stringify(compared_pairs));
            setComparedPairs(compared_pairs);
        },
        complete: function () {
            $('.progress').hide();
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
