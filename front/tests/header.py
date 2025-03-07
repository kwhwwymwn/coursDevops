from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

def run():
    print('header')
    chrome_options = Options()
    chrome_options.add_argument("--headless")
    chrome_options.add_argument('--no-sandbox')
    driver = webdriver.Chrome(options=chrome_options)

    driver.get("http://localhost:4200")

    wait = WebDriverWait(driver, timeout=2)

    try :
        loginBtn = wait.until(EC.presence_of_element_located((By.XPATH, '/html/body/app-root/app-header/div/a[2]/button')))
        loginBtn.click()
        wait.until(EC.url_contains("/login"))
        assert "/login" in driver.current_url

        homeLink = wait.until(EC.presence_of_element_located((By.XPATH, '/html/body/app-root/app-header/div/a[1]')))
        homeLink.click()
        wait.until(EC.url_contains("/"))
        assert "/" in driver.current_url
    except:
        assert False

    driver.close()
    driver.quit()