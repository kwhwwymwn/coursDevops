import header, cart, login

print("\n🔍 Lancement de tous les tests Selenium...\n" + "-"*40)

header.run()
cart.run()
login.run()

print("\n" + "-"*40 + "\n✅ Tous les tests sont terminés !")