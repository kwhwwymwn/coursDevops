from selenium import webdriver
from selenium.webdriver import Keys
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

def run():
    print("searchbar")
    chrome_options = Options()
    chrome_options.add_argument("--headless")
    chrome_options.add_argument('--no-sandbox')
    driver = webdriver.Chrome(options=chrome_options)

    driver.get("http://localhost:4200")

    wait = WebDriverWait(driver, timeout=2)

    existing_product = "Huile de fleur"
    non_existing_product = "ProduitInexistant"

    # Serching an existing product with key enter
    try:
        search_input = wait.until(EC.presence_of_element_located((By.XPATH, "//app-search-bar//input")))
        search_input.clear()
        search_input.send_keys(existing_product)
        search_input.send_keys(Keys.RETURN)

        product_cards = wait.until(EC.presence_of_all_elements_located((By.XPATH, "//app-product-card//h2")))
        found = False
        for card in product_cards:
            if card.text == existing_product:
                found = True
                break

        assert found, f"The product '{existing_product}' was not found after search."

    except Exception as e:
        assert False, f"Error in the first test: {e}"

    # Searching a non existing product with the search button
    try:
        search_input = wait.until(EC.presence_of_element_located((By.XPATH, "//app-search-bar//input")))
        search_button = wait.until(EC.presence_of_element_located((By.XPATH, "//app-search-bar//button")))

        search_input.clear()
        search_input.send_keys(non_existing_product)
        search_button.click()

        # Check if no product cards are found after the search
        product_cards = driver.find_elements(By.XPATH, "//app-product-card")
        assert len(product_cards) == 0, f"No product should match, but product cards are present after search for '{non_existing_product}'."

    except Exception as e:
        assert False, f"Error in the second test: {e}"

    # Empty search with the search button
    try:
        search_input = wait.until(EC.presence_of_element_located((By.XPATH, "//app-search-bar//input")))
        search_button = wait.until(EC.presence_of_element_located((By.XPATH, "//app-search-bar//button")))

        search_input.clear()
        search_button.click()

        product_cards = wait.until(EC.presence_of_all_elements_located((By.XPATH, "//app-product-card")))
        assert len(product_cards) == 3, f"Expected to found 3 cards, but found {len(product_cards)}."

    except Exception as e:
        assert False, f"Error in the third test: {e}"


    driver.close()
    driver.quit()