
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <style>
        html, body {
            height: 100%;
        }
        body {
            background: lightcyan !important;
        }
    </style>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <title>Basket</title>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light" style="background-color: lightskyblue">
    <div class="container">
        <a class="navbar-brand" href="#">Web Market</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/products">Products</a>
                </li>
            </ul>
        </div>
    </div>
</nav>


<div class="container align-top">
    <div class="row">
        <div class="col-sm"><div>
                <div class="col-sm">
                    <table class="table table-striped table-hover">
                        <h3>Basket</h3>
                        <thead>
                        <tr>
                            <th scope="col">id</th>
                            <th scope="col">Name</th>
                            <th scope="col">Price</th>
                            <th scope="col">Description</th>
                            <th scope="col">Delete</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list products as product>
                            <div>
                                <tr>
                                    <th>${product.id}</th><br>
                                    <td>${product.name}</td><br>
                                    <td>${product.price}</td><br>
                                    <td>${product.description}</td><br>
                                    <td>
                                        <a class="btn btn-danger btn-lg" href="/products/cart/delete/${product.id}" role="button">Delete</a>
                                    </td>
                                </tr>
                            </div>
                        </#list>
                        </tbody>
                        <div a href="/products/cart/buy" style="margin-top: 20px">
                            <button type="submit" class="btn btn-primary w-100">Buy</button>
                        </div>
                        <div a href="/products/cart/clear" style="margin-top: 20px">
                            <button type="submit" class="btn btn-primary w-100">Clear</button>
                        </div>
                    </table>
                    <div>
                        <div class="col-sm"><div>
                            </div>
                        </div>

                        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
                                integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</body>
</html>