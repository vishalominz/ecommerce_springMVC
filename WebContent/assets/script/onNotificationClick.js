$(document).ready(function(){
  $("#dLabel").click(function(event){
    $("#notify").text("");
    $.ajax({
      url:"markAsRead.json",
      success:function(responseText){
        console.log(responseText);
      }
    })
  })
})
