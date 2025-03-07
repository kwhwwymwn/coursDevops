from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

def run():
    print("cart")
    chrome_options = Options()
    chrome_options.add_argument("--headless")
    chrome_options.add_argument('--no-sandbox')
    driver = webdriver.Chrome(options=chrome_options)
    
    wait = WebDriverWait(driver, timeout=2)

    #Case : visitor
    driver.get("http://localhost:4200")

    try :
        addToCartBtn = wait.until(EC.presence_of_element_located((By.XPATH, '//app-product-card[1]//button')))
        addToCartBtn.click()
        assert len(driver.find_elements(By.TAG_NAME, "app-cart")) == 0, "The cart component should not be present, but it was found."
        assert len(driver.find_elements(By.TAG_NAME, "app-cart-item")) == 0, "No cart items should be present, but some were found."
    except:
        assert False, "Test fail as visitor"



    #Case : admin
    driver.get("http://localhost:4200?userType=2")

    try :
        productCards = wait.until(EC.presence_of_all_elements_located((By.TAG_NAME, "app-product-card")))
    
        for card in productCards:
            try:
                addToCartBtn = card.find_element(By.XPATH, ".//button[.//span[text()='Ajouter au panier']]")
                assert False, "'Ajouter au panier' button should not be present for admin users."
            except:
                pass
    except:
        assert False, "Test fail as admin"



    #Case : customer
    driver.get("http://localhost:4200?userType=1")

    try:
        assert len(driver.find_elements(By.TAG_NAME, "app-cart")) == 1

        cardTitle1 = wait.until(EC.presence_of_element_located((By.XPATH, "//app-product-card[1]//h2"))).text
        addToCartBtn1 = wait.until(EC.presence_of_element_located((By.XPATH, "//app-product-card[1]//button")))
        addToCartBtn1.click()

        cardTitle2 = wait.until(EC.presence_of_element_located((By.XPATH, "//app-product-card[2]//h2"))).text
        addToCartBtn2 = wait.until(EC.presence_of_element_located((By.XPATH, "//app-product-card[2]//button")))
        addToCartBtn2.click()

        cartItems = wait.until(EC.presence_of_all_elements_located((By.XPATH, "//app-cart-item")))
        assert len(cartItems) == 2, f"There should be 2 items in the cart, but there are {len(cartItems)}"

        cartItemTitles = [item.find_element(By.XPATH, ".//h2").text for item in cartItems]
        assert cardTitle1 in cartItemTitles and cardTitle2 in cartItemTitles, f"The items in the cart do not match the added products. Expected: {cardTitle1}, {cardTitle2}, found: {cartItemTitles}"

    except:
        assert False, "Fail to add to cart as customer"

    try:
        cartItems = wait.until(EC.presence_of_all_elements_located((By.XPATH, "//app-cart-item")))

        assert len(cartItems) == 2, f"There should be 2 items in the cart, but there are {len(cartItems)}"

        itemToRemove = cartItems[0]
        itemTitleToRemove = itemToRemove.find_element(By.XPATH, ".//h2").text

        removeBtn = itemToRemove.find_element(By.XPATH, ".//button")
        removeBtn.click()

        remainingItems = driver.find_elements(By.XPATH, "//app-cart-item")
        assert len(remainingItems) == 1, f"The cart should contain 1 item after removal, but there are {len(remainingItems)}."

        remainingItemTitle = remainingItems[0].find_element(By.XPATH, ".//h2").text
        assert remainingItemTitle != itemTitleToRemove, f"The remaining product is the same as the one removed. Removed product: {itemTitleToRemove}, remaining product: {remainingItemTitle}"

    except:
        assert False, "Fail to remove item from cart as customer"

    driver.close()
    driver.quit()