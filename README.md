https://img.shields.io/badge/build-in_process-orange
<h1>Orpheu-Web-API<h1>
<h2>Tecnologias utilizadas</h2>
<div style="margin:0 auto">
    
</div>
<h2>Diagrama de classes e relacionamentos</h2>
```mermaid
classDiagram
    class User {
        -Long id
        -String username
        -String password
        -String email
        -String role
    }

    class Customer {
        -Long id
        -String customerName
        -String phone
        -String address
    }

    class Supplier {
        -Long id
        -String supplierName
        -String supplierRegister
        -String phone
        -String address
    }

    class SupplierOrder {
        -Long id
        -Supplier supplierId
        -Date supplier_order_date
        -Double total
    }

    class SupplierOrderProduct {
        -Long id
        -Supplier supplierOrderId
        -Product productId
        -Double amount
    }

    class Product {
        -Long id
        -String productName
        -Category categoryId
        -Double costPrice
        -Double sellingPrice
        -Integer amount
    }

    class CustomerOrderProduct {
        -Long id
        -CustomerOrder customerOrderId
        -Product productId
        -Double amount
    }

    class CustomerOrder {
        -Long id
        -Customer customerId
        -Date customerOrderDate
        -Double total
    }

    class Category {
        -Long id
        -String category_name
    }

    %% Relationships
    Category "1" -- "*" Product : OneToMany
    Customer "1" -- "*" CustomerOrder : OneToMany
    CustomerOrder "1" -- "*" CustomerOrderProduct : OneToMany
    Product "1" -- "*" CustomerOrderProduct : OneToMany
    Supplier "1" -- "*" SupplierOrder : OneToMany
    SupplierOrder "1" -- "*" SupplierOrderProduct : OneToMany
    Product "1" -- "*" SupplierOrderProduct : OneToMany
