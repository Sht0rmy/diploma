fetch('/api/order/my-orders', {
    method: 'GET'
}).then(response => response.json())
.then(data => {
    if(data.length > 0){
        const orderContainer = document.getElementById('orders-container')

        for(let i = 0; i < data.length; i++){
            const order = data[i]

            const orderBlock = document.createElement('div')
            orderBlock.className = 'order'
            orderBlock.innerHTML = `
                <p id='order-${i}'></p>
                <p><strong>ПІБ:</strong> ${order.pib}</p>
                <p><strong>Адреса доставки:</strong> ${order.delivery}</p>
                <p><strong>Номер телефону:</strong> ${order.phone}</p>
                <p><strong>Пошта:</strong> ${order.email}</p>
                <p><strong>Дата:</strong> ${order.date}</p>
            `

            orderContainer.appendChild(orderBlock)

            const productBlock = document.getElementById(`order-${i}`)
            let productText = ''
            for(let i = 0; i < order.products.length; i++){
                const product = order.products[i]

                productText = productText + product.title

                if(i < order.products.length -1)
                    productText += ','
            }
            productBlock.innerHTML = `
                <strong>Товари: </strong>${productText} 
            `
        }
    }
})