# :leaves: API Calculo de Frete  :leaves:

## Sobre o Projeto

Calculo de frete é uma coleção de duas APIs de estudo desenvolvidas em **Spring** no Bootcamp da Digital House

## API Stock

Tem como base o funcionamento de um armazém onde serão armazenados os produtos

| Field   | Description                                                |
|---------|------------------------------------------------------------|
| Port    | 8081                                                       |
| BaseURL | http://localhost:8081/api/v1/                              |
| DB      | H2, MYSQL                                                  |                               
| Swagger | [Swagger-UI](http://localhost:8081/api/v1/swagger-ui.html) |


## API Shipment
Tem como base o funcionamento de um serviço de entregas onde baseado na quantidade de produtos, endereço do remetente e
endereço do destinatario, é feito um cálculo com base na API dos [Correios](https://www.correios.com.br/) 
onde será devolvido o valor aproximado do frete com base na taxa atual utilizada pela propria empresa.

Tais produtos são diretamente consumidos da _API Stock_ acima, passando por todas as validações necessarias e ambos os
endereços referenciados são preenchidps com base na API da [ViaCEP](https://viacep.com.br/)

| Field   | Description                                                 |
|---------|-------------------------------------------------------------|
| Port    | 8080                                                        |
| BaseURL | http://localhost:8080/api/v1/                               |
| DB      | MongoDB                                                     |                               
| Swagger | [Swagger-UI](http://localhost:8080/api/v1/swagger-ui.html)  |

**Request | Response Models**

[POST] : <http://localhost:8080/api/v1/buyers>
#
```
{
    "name": "Fulano da Silva",
    "endereco": {
      "cep": "01000-000"
    }
}
```
```
{
    "id": "620648134686cd1fdfa58a83",
    "name": "Fulano da Silva",
    "code": "COD116788",
    "endereco": {
        "cep": "01001-000",
        "logradouro": "Praça da Sé",
        "bairro": "Sé",
        "uf": "SP"
    },
    "cart": {
        "products": [],
        "cartStatus": "ABERTO",
        "total": 0.0,
        "dataCriacao": "2022-02-10"
    }
}
```
[POST] : <http://localhost:8080/api/v1/purchase/add-products?buyerCode=COD116788>
#
```
[
   {
    "id": 1,
    "quantity": 1
   }
]
```

```
{
  "products": [
        {
            "name": "PRODUTO",
            "quantity": 1,
            "dueDate": "2022-03-02",
            "price": 20.0
        }
    ],
    "cartStatus": "ABERTO",
    "total": 20.00,
    "dataCriacao": "2022-02-10"
}
```
[POST] : <http://localhost:8080/api/v1/purchase/finalize-purchase>
#
```
{
    "buyerCod": "COD116788",
    "shippingService": "40010"
}
```
```
{
    "dataDaCompra": "2022-02-10",
    "boxes": [
        {
            "products": [
                {
                    "name": "SALSICHA",
                    "quantity": 1,
                    "dueDate": "2022-03-02",
                    "price": 20.0
                }
            ],
            "valorFrete": 22.50,
            "previsaoEntrega": "2022-02-13",
            "remetente": {
                "name": "CENTRO DE DISTRIBUIÇÃO OSASCO",
                "endereco": {
                    "cep": "06016-040",
                    "logradouro": "Rua General Bitencourt",
                    "bairro": "Centro",
                    "uf": "SP"
                }
            }
        }
    ],
    "envio": "SEDEX s/ contrato",
    "totalPurchase": 42.50
}
```
## PDF Projeto
<https://drive.google.com/file/d/1hwU_AX-GhkjUl12B5IZ_KNlVo3UmpGYE/view?usp=sharing>

## Contato
E-mail: everton.vsilva@mercadolivre.com
