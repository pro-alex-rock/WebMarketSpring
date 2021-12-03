
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

    <title>Products</title>
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
                <li class="nav-item">
                    <a class="nav-link" href="/products/add">Add product</a>
                </li>
            </ul>
            <form class="d-flex" action="/products/search">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
        </div>
    </div>
</nav>


<div class="container align-top">
    <div class="row">
        <div class="col-sm"><div>
            <div class="col-sm">
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th scope="col">id</th>
                <th scope="col">Name</th>
                <th scope="col">Price</th>
                <th scope="col">Description</th>
                <th scope="col">Add to cart</th>
                <th scope="col">Update</th>
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
                        <a class="btn btn-success btn-lg" href="/products/cart/${product.name}" role="button">Add to cart</a>
                    </td><br>
                    <td>
                        <a class="btn btn-danger btn-lg" href="/products/edit/${product.id}" role="button">Update</a>
                    </td>
                    <td>
                        <a class="btn btn-danger btn-lg" href="/products/delete/${product.id}" role="button">Delete</a>
                    </td>
                </tr>
            </div>
            </#list>
            </tbody>
        </table>
                <div>
            <div class="col-sm"><div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</body>
</html>