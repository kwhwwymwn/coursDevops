from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

def run():
    print('login')
    chrome_options = Options()
    chrome_options.add_argument("--headless")
    chrome_options.add_argument('--no-sandbox')
    driver = webdriver.Chrome(options=chrome_options)
    
    driver.get("http://localhost:4200/login")

    wait = WebDriverWait(driver, timeout=2)

    #login en tant que client
    try:
        loginField = wait.until(EC.presence_of_element_located((By.XPATH, '/html/body/app-root/app-login-page/div/div/form/input[1]')))
        passwordField = wait.until(EC.presence_of_element_located((By.XPATH, '/html/body/app-root/app-login-page/div/div/form/input[2]')))
        loginBtn = wait.until(EC.presence_of_element_located((By.XPATH, '/html/body/app-root/app-login-page/div/div/button')))

        loginField.send_keys('customer')
        passwordField.send_keys('1234')
        loginBtn.click()

        wait.until(EC.url_contains("/"))
        assert "/?userType=1" in driver.current_url
    except:
        assert False

    driver.get("http://localhost:4200/login")

    #login en tant qu'administrateur
    try:
        loginField = wait.until(EC.presence_of_element_located((By.XPATH, '/html/body/app-root/app-login-page/div/div/form/input[1]')))
        passwordField = wait.until(EC.presence_of_element_located((By.XPATH, '/html/body/app-root/app-login-page/div/div/form/input[2]')))
        loginBtn = wait.until(EC.presence_of_element_located((By.XPATH, '/html/body/app-root/app-login-page/div/div/button')))

        loginField.send_keys('admin')
        passwordField.send_keys('admin')
        loginBtn.click()

        wait.until(EC.url_contains("/"))
        assert "/?userType=2" in driver.current_url
    except:
        assert False
    
    driver.get("http://localhost:4200/login")

    #login avec des identifiants incorrects
    try:
        loginField = wait.until(EC.presence_of_element_located((By.XPATH, '/html/body/app-root/app-login-page/div/div/form/input[1]')))
        passwordField = wait.until(EC.presence_of_element_located((By.XPATH, '/html/body/app-root/app-login-page/div/div/form/input[2]')))
        loginBtn = wait.until(EC.presence_of_element_located((By.XPATH, '/html/body/app-root/app-login-page/div/div/button')))
        errorMsg = wait.until(EC.presence_of_element_located((By.XPATH, '/html/body/app-root/app-login-page/div/div/p')))

        loginField.send_keys('toto')
        passwordField.send_keys('toto')
        loginBtn.click()

        wait.until(EC.url_contains("/"))
        assert "/login" in driver.current_url
        assert errorMsg.text == "Identifiant ou mot de passe incorrect"
    except:
        assert False

    driver.get("http://localhost:4200/login")

    #login sans remplir les champs
    try:
        loginBtn = wait.until(EC.presence_of_element_located((By.XPATH, '/html/body/app-root/app-login-page/div/div/button')))
        errorMsg = wait.until(EC.presence_of_element_located((By.XPATH, '/html/body/app-root/app-login-page/div/div/p')))

        loginBtn.click()

        wait.until(EC.url_contains("/"))
        assert "/login" in driver.current_url
        assert errorMsg.text == "Veuillez remplir tous les champs"
    except:
        assert False

    driver.close()
    driver.quit()