import header, cart, login, newProduct

print("\n🔍 Lancement de tous les tests Selenium...\n" + "-"*40)

header.run()
cart.run()
login.run()
newProduct.run()

print("\n" + "-"*40 + "\n✅ Tous les tests sont terminés !")