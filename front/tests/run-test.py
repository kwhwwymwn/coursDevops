import header, cart, login, searchbar

print("\n🔍 Lancement de tous les tests Selenium...\n" + "-"*40)

header.run()
cart.run()
login.run()
searchbar.run()

print("\n" + "-"*40 + "\n✅ Tous les tests sont terminés !")