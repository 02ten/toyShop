$(document).ready(function (){


    $(".addToCart").on("click", function (e){
        quantity= $("#quantity"+productId).val();
        url="/" + "cart/" +productId+"/" +quantity;
        console.log(url)
        $.ajax({
            type: "POST",
            url: url
            }).done(alert("Товар был успешно добавлен в корзину"));
    });
});