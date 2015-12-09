function start_sse() {
  var source = new EventSource('/sse');
  var start = new Date();
  var countDown = 3;

  source.onopen = function(event) {
    $('#response').val("");
    $(".segment").dimmer("show");
  };

  source.onmessage = function(event) {
    var data = event.data;
    $('#response').val($('#response').val() + event.data + "\n");

    if (--countDown == 0) {
      source.close();
      $(".segment").dimmer("hide");
      $("#elapse").html(new Date() - start);
    }
  };
}


$(function() {
  $("#link-sse").on("click", function(e) {
    start_sse();
  });

  $("#link-sequentialsync").on("click", function(e) {
    var start = new Date();
    $(".segment").dimmer("show");
    $.ajax({
      url: "/sequentialsync",
      dataType: "text",
      success: function(data) {
        $("#response").val(data);
      },
      complete: function() {
        $("#elapse").html(new Date() - start);
        $(".segment").dimmer("hide");
      }
    });

  });

  $("#link-parallelsync").on("click", function(e) {
    var start = new Date();
    $(".segment").dimmer("show");
    $.ajax({
      url: "/parallelsync",
      dataType: "text",
      success: function(data) {
        $("#response").val(data);
      },
      complete: function() {
        $("#elapse").html(new Date() - start);
        $(".segment").dimmer("hide");
      }
    });
  });
});
