fetch('/api/user/get-basket', {
    method: 'GET'
}).then(response => response.json())
.then(data => {
    if(data.length > 0){
        const basketBlock = document.getElementById('shop-product-list')

        for(let i = 0; i < data.length; i++){
            const product = data[i]

            const productBlock = document.createElement('li')
            productBlock.className = 'shop-product-item'
            productBlock.innerHTML = `
                <img src="/images/${product.previewImage}" alt="Товар 1">
                <div class="shop-product-info">
                    <p class="shop-product-name">${product.title}</p>
                    <p class="shop-product-price">${product.price} грн.</p>
                </div>
            `

            basketBlock.appendChild(productBlock)
        }

        const acceptButton = document.getElementById('accept-basket')
        acceptButton.addEventListener('click', () => {
            const products = data
            fetch('/api/user/get-me', {
                method: 'GET'
            }).then(response => response.json())
                .then(data => {
                    document.body.innerHTML = ''

                    document.body.innerHTML = `
                        <header>
                        <div class="top-bar">
                            <div class="login-register">
                                <a href="/cabinet"><i class="fas fa-user"></i></a><a href="/login">Логін/Реєстрація</a>
                            </div>
                            <div class="phone">
                                <i class="fas fa-phone-alt"></i> +380947685198
                            </div>
                            <div class="email">
                                <i class="far fa-envelope"></i> info@gmail.com
                            </div>
                            <div class="social-media">
                                <a href="#"><i class="fab fa-facebook-f"></i></a>
                                <a href="#"><i class="fab fa-twitter"></i></a>
                                <a href="#"><i class="fab fa-pinterest"></i></a>
                                <a href="#"><i class="fab fa-google"></i></a>
                            </div>
                        </div>
                    </header>
                    <div class="menu-bar">
                        <ul>
                            <li><a href="/">Головна</a></li>
                            <li><a href="#">Каталог</a></li>
                            <li><a href="#">Контакти</a></li>
                            <li><a href="#">Новинки</a></li>
                        </ul>
                        <button><a class="basket" href="/basket">Оформити замовлення</a></button>
                    </div>
                    <div class="full-width-image">
                        <img src="/img/banner.jpg" alt="Фото">
                        <p class="name-full-window">Відеокарта MSI GeForce RTX3060 12Gb <br> VENTUS 2X OC (RTX 3060 VENTUS 2X 12G OC)</p>
                    </div>
                    <div class="container-wrapper">
                        <div class="user-container" id="user-container">
                            <form class="form" id="personal-details-form">
                                <h2>Дані для замовлення</h2>
                                <div class="form-group">
                                    <label for="pib">ПІБ:</label>
                                    <input type="text" id="pib" name="pib" value='${data.name}' required>
                                </div>
                                <div class="form-group">
                                    <label for="delivery">Адреса доставки:</label>
                                    <input type="text" id="delivery" name="delivery" value='${data.delivery}' required>
                                </div>
                                <div class="form-group">
                                    <label for="phone">Номер телефону:</label>
                                    <input type="tel" id="phone" name="phone" value='${data.phone}' required>
                                </div>
                                <div class="form-group">
                                    <label for="email">Пошта:</label>
                                    <input type="email" id="email" name="email" value='${data.email}' required>
                                </div>
                            </form>                    
                                <button id="accept">Створити замовлення</button>
                        </div>
                    </div>
                    
                    <footer>
                        <div class="footer-content">
                            <p class="copyright">2024 всі права захищені</p>
                            <div class="social-media">
                                <a href="#"><i class="fab fa-facebook-f"></i></a>
                                <a href="#"><i class="fab fa-twitter"></i></a>
                                <a href="#"><i class="fab fa-pinterest"></i></a>
                                <a href="#"><i class="fab fa-google"></i></a>
                            </div>
                        </div>
                    </footer>
                    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
                    <script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.js"></script>
                    <script>
                        $(document).ready(function() {
                            $('.product-images').slick({
                                dots: false,
                                infinite: true,
                                speed: 300,
                                slidesToShow: 4,
                                slidesToScroll: 1,
                                arrows: true,
                                autoplay: false,
                                autoplaySpeed: 2000,
                                arrows: true,
                                prevArrow: '<button type="button" class="slick-prev">Previous</button>',
                                nextArrow: '<button type="button" class="slick-next">Next</button>'
                            });
                        });
                    </script>
                    `

                    const accept = document.getElementById('accept')
                    accept.addEventListener('click', () => {
                        const pib = document.getElementById('pib').value
                        const delivery = document.getElementById('delivery').value
                        const phone = document.getElementById('phone').value
                        const email = document.getElementById('email').value

                        const dataToSend = {
                            products, pib, delivery, phone, email
                        }

                        fetch('/api/order/create-order', {
                            method: 'POST',
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            body: JSON.stringify(dataToSend)
                        })
                            .then(response => response.text())
                            .then(data => {
                                alert(data)
                                window.location.href = `/`
                            })
                    })
                })
        })
    }
})