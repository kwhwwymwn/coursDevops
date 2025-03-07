from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

def run():
    print('newProduct')
    chrome_options = Options()
    chrome_options.add_argument("--headless")
    chrome_options.add_argument('--no-sandbox')
    driver = webdriver.Chrome(options=chrome_options)
    driver = webdriver.Chrome()
    driver.get("http://localhost:4200?userType=2")
    
    wait = WebDriverWait(driver, timeout=2)

    #ajout d'un nouveau produit et vérification de la nouvelle card ainsi que du vidage du formulaire
    try:
        name = 'test'
        price = 10
        tva = 1
        image = 'https://www.google.com/images/branding/googlelogo/2x/googlelogo_light_color_272x92dp.png'

        nameField = wait.until(EC.presence_of_element_located((By.XPATH, '/html/body/app-root/app-main-page/div/div[2]/app-new-product/div/div/form/input[1]')))
        priceField = wait.until(EC.presence_of_element_located((By.XPATH, '/html/body/app-root/app-main-page/div/div[2]/app-new-product/div/div/form/input[2]')))
        tvaField = wait.until(EC.presence_of_element_located((By.XPATH, '/html/body/app-root/app-main-page/div/div[2]/app-new-product/div/div/form/input[3]')))
        imageField = wait.until(EC.presence_of_element_located((By.XPATH, '/html/body/app-root/app-main-page/div/div[2]/app-new-product/div/div/form/input[4]')))
        sendBtn = wait.until(EC.presence_of_element_located((By.XPATH, '/html/body/app-root/app-main-page/div/div[2]/app-new-product/div/div/form/button')))

        nameField.send_keys(name)
        priceField.send_keys(price)
        tvaField.send_keys(tva)
        imageField.send_keys(image)
        sendBtn.click()

        cardTitle = wait.until(EC.presence_of_element_located((By.XPATH, '/html/body/app-root/app-main-page/div/div[1]/app-catalogue/div[2]/app-product-card[4]/div/h2')))
        cardPrice = wait.until(EC.presence_of_element_located((By.XPATH ,'/html/body/app-root/app-main-page/div/div[1]/app-catalogue/div[2]/app-product-card[4]/div/div/h3')))
        cardTva = wait.until(EC.presence_of_element_located((By.XPATH, '/html/body/app-root/app-main-page/div/div[1]/app-catalogue/div[2]/app-product-card[4]/div/div/p')))
        cardImg = wait.until(EC.presence_of_element_located((By.XPATH, '/html/body/app-root/app-main-page/div/div[1]/app-catalogue/div[2]/app-product-card[4]/div/img')))

        assert cardTitle.text == name
        assert cardPrice.text == str(price) + '€'
        assert cardTva.text == 'Dont ' + str(tva) + '€ de TVA'
        assert cardImg.get_attribute('src') == image

        assert nameField.text == ''
        assert imageField.text == ''
        assert priceField.text == ''
        assert tvaField.text == ''
    except:
        assert False

    #tentative de création sans remplissage des champs
    try:
        sendBtn = wait.until(EC.presence_of_element_located((By.XPATH, '/html/body/app-root/app-main-page/div/div[2]/app-new-product/div/div/form/button')))
        errorMsg = wait.until(EC.presence_of_element_located((By.XPATH, '/html/body/app-root/app-main-page/div/div[2]/app-new-product/div/div/p')))

        sendBtn.click()
        assert errorMsg.text == 'Veuillez remplir tous les champs'
    except:
        assert False


    driver.close()
    driver.quit()