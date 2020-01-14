package me.sat7.dynamicshop;

import kr.dja.aldarEconomy.AldarEconomyCore;
import kr.dja.aldarEconomy.api.AldarEconomy;
import kr.dja.aldarEconomy.api.EconomyResult;
import kr.dja.aldarEconomy.api.token.SystemID;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class DynamicShopEconomyManager {
    private AldarEconomy economy;
    private SystemID aldarToken;

    public DynamicShopEconomyManager(JavaPlugin plugin) {
        AldarEconomyCore core = (AldarEconomyCore) plugin.getServer().getPluginManager().getPlugin("AldarEconomy");
        economy = core.getEconomyModule();
        if(economy == null) throw new NullPointerException("AldarEconomy is Null");
        aldarToken = economy.takeAPIToken("DynamicShop");
    }

    public String format(double money) {
        return economy.economyFormat((int)money);
    }


    public int getMoney(OfflinePlayer player) {
        return economy.getPlayerInventoryMoney(player);
    }

    public EconomyResult depositPlayerItem(HumanEntity player, double amount, ItemStack item) {
        return economy.depositPlayer(player, (int)amount, aldarToken, "tradeSell", DynaShopAPI.makeJson(item));
    }

    public EconomyResult withdrawPlayerItem(HumanEntity player, double amount, ItemStack item) {
        return economy.withdrawPlayer(player, (int)amount, aldarToken, "tradeBuy", DynaShopAPI.makeJson(item));
    }

    public EconomyResult depositPlayerShop(HumanEntity player, double amount, String shopName) {
        return economy.depositPlayer(player, (int)amount, aldarToken,  "shopTotalizationDeposit", shopName);
    }
}
