package com.Connections;

import java.util.Set;

import com.Project0.Account;

public interface AccountDao {
	Set<Account> getAccounts();
	void saveAccounts(Set<Account> accounts);
	Set<Account> getPendingAccounts();
	void savePendingAccounts(Set<Account> accounts);
	void clear();
}
