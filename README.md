##Diagrama de classes e relacionamentos: 

```mermaid
classDiagram
    class Users {
        -Long id
        -String user_name
        -String password
        -String email
        -String role
    }

    class Customers {
        -Long id
        -String customer_name
        -String phone
        -String address
    }

    class Suppliers {
        -Long id
        -String supplier_name
        -String supplier_register
        -String phone
        -String address
    }

    class SupplierOrders {
        -Long id
        -Long supplier_id
        -Date supplier_order_date
        -Double total
    }

    class SupplierOrderProducts {
        -Long id
        -Long supplier_order_id
        -Long product_id
        -Double amount
    }

    class Products {
        -Long id
        -String product_name
        -Long category_id
        -Double cost_price
        -Double selling_price
        -Integer amount
    }

    class CustomerOrderProducts {
        -Long id
        -Long order_id
        -Long product_id
        -Double amount
    }

    class CustomerOrders {
        -Long id
        -Long customer_id
        -Date customer_order_date
        -Double total
    }

    class Categories {
        -Long id
        -String category_name
    }

    %% Relationships
    Categories "1" -- "0..*" Products : "has many"
    Customers "1" -- "0..*" CustomerOrders : "places"
    CustomerOrders "1" -- "0..*" CustomerOrderProducts : "contains"
    Products "1" -- "0..*" CustomerOrderProducts : "ordered in"
    Suppliers "1" -- "0..*" SupplierOrders : "places"
    SupplierOrders "1" -- "0..*" SupplierOrderProducts : "contains"
    Products "1" -- "0..*" SupplierOrderProducts : "supplied in"
