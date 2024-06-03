fetch('/api/user/get-me', {
    method: 'GET'
}).then(response => response.json())
.then(data => {
    if(data.avatar !== -1) {
        const avatar = document.getElementById('avatar-img')
        avatar.src = `/images/${data.avatar}`
    }

    const userBlock = document.getElementById('product-container')

    const block = document.createElement('div')
    block.className = 'product-details'
    block.id = 'product-details'
    block.innerHTML = `
        <div class="product-guaranty" style="font-size:25px">${data.email}</div>
        <div class="product-guaranty" style="font-size:25px">${data.name}</div>
        <div class="product-guaranty" style="font-size:25px">${data.phone}</div>
        <div class="product-guaranty" style="font-size:25px">${data.delivery}</div>
    `

    userBlock.appendChild(block)

    const productDetails = document.getElementById('product-details')
    const buttonsBlock = document.createElement('div')
    buttonsBlock.className = 'buttons'
    buttonsBlock.style.display = 'flex'
    buttonsBlock.innerHTML = `
        <div class="by-btn-product"><button class="profile-button" id="by-on-produst">Список бажаного</button></div>    
        <div class="by-btn-product"><button class="profile-button" id="order-list">Список замовлень</button></div>    
        <div class="by-btn-product"><button class="profile-button" id="change-data">Змінити дані</button></div> 
    `
    productDetails.appendChild(buttonsBlock)


    document.getElementById('by-on-produst').addEventListener('click', () => {
        window.location.href = `/wishlist`
    })

    document.getElementById('change-data').addEventListener('click', () => {
        window.location.href = `/change-data`
    })

    document.getElementById('order-list').addEventListener('click', () => {
        window.location.href = `/my-orders`
    })
})