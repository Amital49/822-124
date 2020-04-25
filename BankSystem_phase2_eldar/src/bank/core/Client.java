package bank.core;

/** a client is a customer working with the bank */
public class Client {

	// attributes
	private int id;
	private String name;
	private float balance;
	private Account[] accounts = new Account[5];
	// the rate (percentage) for commissions a client needs to give
	private float commissionRate;
	// the rate (percentage) for interest a client will earn from the bank
	private float interestRate;
	private Logger logger;

	// CTOR
	public Client(int id, String name, float balance) {
		super();
		this.id = id;
		this.name = name;
		this.balance = balance;
		this.logger = new Logger(null);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public int getId() {
		return id;
	}

	public Account[] getAccounts() {
		return accounts;
	}

	/**
	 * add the account to the array and log the operation. You should seek the array
	 * and place the Account where the first null value is found.
	 */
	public void addAccount(Account account) {
		for (int i = 0; i < accounts.length; i++) {
			if (accounts[i] == null) {
				accounts[i] = account;
				// LOG THE OPERATION ===
				long timestamp = System.currentTimeMillis();
				int clientId = this.id;
				String description = "account added: " + account.getId();
				float amount = account.getBalance();
				Log log = new Log(timestamp, clientId, description, amount);
				this.logger.log(log);
				// =====================
				return;
			}
		}
		// if we are here - the array of accounts was full
		System.out.println("Account not added because you already have " + accounts.length + " accounts");
	}

	/**
	 * returns the account of the specified index or null if does not exist
	 */
	public Account getAccount(int index) {
		if (index >= 0 && index <= accounts.length) {
			return accounts[index];
		} else {
			return null;
		}
	}

	/**
	 * remove the account with the same id from the array (by assigning a 'null'
	 * value to the array[position]) & transfers the money to the clients balance.
	 * Log the operation via creating Log object with appropriate data and sending
	 * it to the Logger.log(..) method.
	 */
	public void removeAccount(int accountId) {
		for (int i = 0; i < accounts.length; i++) {
			Account curr = accounts[i];
			if (curr != null && curr.getId() == accountId) {
				accounts[i] = null; // remove the account by setting the array position to null
				this.balance += curr.getBalance(); // transfers the money to the clients balance
				// LOG THE OPERATION ===
				long timestamp = System.currentTimeMillis();
				int clientId = this.id;
				String description = "account removed: " + curr.getId();
				float amount = curr.getBalance();
				Log log = new Log(timestamp, clientId, description, amount);
				this.logger.log(log);
				// =====================
				return;
			}
		}

		System.out.println("account id " + accountId + " was not removed (not found)");
	}

}
