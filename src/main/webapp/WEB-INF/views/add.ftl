<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        html, body {
            height: 100%;
        }
        body {
            background: lightcyan !important;
        }
    </style>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css" type="text/css">

    <title>Products</title>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light" style="background-color: lightskyblue">
    <div class="container">
        <a class="navbar-brand" href="#">Web Market</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
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

<div class="input_product d-flex h-40 justify-content-center p-0">
    <div class="row bg-white shadow-lg-3">
        <div class="col-sm"><div>
            <div class="tableName col-sm" style="margin: 10px; padding: 10px">
                <form action="/products/add" method="post">
                    <div>
                        <label for="name" class="form-label text-white">Name:</label>
                        <input type="text" name="name" class="form-control" id="name">
                    </div>
                    <div>
                        <label for="price" class="form-label">Price:</label>
                        <input type="text" name="price" class="form-control" id="price">
                    </div>
                    <div>
                        <label for="description" class="form-label">Description:</label>
                        <input type="text" name="description" class="form-control" id="description">
                    </div>
                    <div a href="/products" style="margin-top: 20px">
                    <button type="submit" class="btn btn-primary w-100">Add</button>
                    </div>
                </form>
            </div>
        <div class="col-sm"><div>
    </div>
</div>


<!-- Option 1: Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

<!-- Option 2: Separate Popper and Bootstrap JS -->
<!--
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
-->
</body>
</html>