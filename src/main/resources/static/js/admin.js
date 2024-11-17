console.log("Admin page");

document.querySelector('#image_file_input').addEventListener("change", function(event) {

    var file = event.target.files[0];
    var reader = new FileReader();

    reader.onload = function() {
        document.querySelector('#upload_image_preview')
        .setAttribute("src", reader.result);
    };

    reader.readAsDataURL(file);
})