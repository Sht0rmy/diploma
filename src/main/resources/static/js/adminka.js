document.getElementById('categories').addEventListener('click', renderCategoriesForms)
const mainElement = document.querySelector('main');

function renderCategoriesForms() {
    window.location.href = `/create-category`
//
//     mainElement.innerHTML = `
//         <form action="#" method="post" id="addCategoryForm" class="categoriesForm"">
//             <h2>Додати категорію</h2>
//             <div>
//                 <label for="newCategoryName">Назва категорії:</label>
//                 <input type="text" id="newCategoryName" name="newCategoryName" required>
//             </div>
//             <div>
//                 <button id="create-product-type" type="submit">Додати категорію</button>
//             </div>
//         </form>
//     `
//
//     document.getElementById('create-product-type').addEventListener('click', () => {
//         let type = document.getElementById('newCategoryName').value
//         fetch(`/api/create-product-type?type=${type}`, {
//             method: 'POST',
//             headers: {
//                 'Content-Type': 'application/json'
//             }
//         })
//     })
}

document.getElementById('users').addEventListener('click', () => {
    fetch('/api/user/get-all', {
        method: 'GET'
    }).then(response => response.json())
        .then(data => {
            if(data.length > 0){
                const databaseContainer = document.createElement('div')
                databaseContainer.className = 'database-table-background'

                databaseContainer.innerHTML = `
                <div class="database-table">
                   <table id="db-table">
                     <tr>
                       <td>#</td>
                       <td>Email</td>
                     </tr>

                   </table>
               </div>
            `

                mainElement.innerHTML = ``
                mainElement.appendChild(databaseContainer)

                const table = document.getElementById('db-table')
                for (let i = 0; i < data.length; i++) {
                    const line = document.createElement('tr')
                    line.innerHTML = `
                        <td>${i}</td>
                        <td>${data[i].email}</td>
                    `
                    table.appendChild(line)
                }
            }
        })
})

document.getElementById('orders').addEventListener('click', () => {
    fetch('/api/order/get-all', {
        method: 'GET'
    }).then(response => response.json())
        .then(data => {
            if (data.length > 0){
                const containerWrapper = document.createElement('div')
                containerWrapper.className = 'container-wrapper-orders'
                containerWrapper.innerHTML = `
                    <div class="orders-container" id="orders-container">
                        <h2>Замовлення</h2>
                
                    </div>
                `

                mainElement.innerHTML = ``
                mainElement.appendChild(containerWrapper)

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
})

document.getElementById('stats').addEventListener('click', () => {
    fetch('/api/product/get-all', {
        method: 'GET'
    }).then(response => response.json())
        .then(data => {
            if(data.length > 0){
                const databaseContainer = document.createElement('div')
                databaseContainer.className = 'database-table-background'

                databaseContainer.innerHTML = `
                <div class="database-table">
                   <table id="db-table">
                     <tr>
                       <td>#</td>
                       <td>Title</td>
                       <td>Views</td>
                       <td>Saves</td>
                       <td>Baskets</td>
                     </tr>
                   </table>
               </div>
            `

                mainElement.innerHTML = ``
                mainElement.appendChild(databaseContainer)

                const table = document.getElementById('db-table')
                for (let i = 0; i < data.length; i++) {
                    const line = document.createElement('tr')
                    line.innerHTML = `
                        <td>${i}</td>
                        <td>${data[i].title}</td>
                        <td>${data[i].views}</td>
                        <td>${data[i].saves}</td>
                        <td>${data[i].baskets}</td>
                    `
                    table.appendChild(line)
                }
            }
        })
})