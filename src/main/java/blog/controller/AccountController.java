package blog.controller;

import blog.model.Account;
import blog.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountRepository accountRepository;

    @Autowired /* constructor injection is preferable as it is explicit */
    public AccountController(AccountRepository accountRepository)
    {
        this.accountRepository = accountRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Account> getByUsername(@RequestParam(value = "username", required = false) String username)
    {
        if (username == null)
            return accountRepository.findAll();
        else
            return accountRepository.findByUsername(username);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Account createAccount(@RequestBody Account account)
    {

        if (accountRepository.findByUsername(account.getUsername()).isEmpty())
        {
            return accountRepository.save(account);
        }
        else {
            System.out.println("Error creating new account. An account by that username already exists.");
            return null;
        }
    }

    @RequestMapping(value="/bulkCreate", method = RequestMethod.POST)
    public void bulkCreate(@RequestBody List<Account> accountList)
    {
        accountList.forEach(account->createAccount(account));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Account updateAccount(@RequestBody Account account)
    {
        List<Account> accountList = accountRepository.findByUsername(account.getUsername());
        if (accountList.isEmpty())
        {
            System.out.println("Error updating account. An account with that username was not found.");
            return null;
        }
        else {
            Account updatedAccount = accountList.get(0);
            updatedAccount.setBalance(account.getBalance());
            updatedAccount.setAccountType(account.getAccountType());
            return accountRepository.save(updatedAccount);
        }
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Account getById(@PathVariable("id") Long id)
    {
        return accountRepository.findById(id);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public void deleteAccount(@PathVariable("id") Long id)
    {
        Account account = accountRepository.findById(id);
        accountRepository.delete(account);
        //return null;
    }

}
