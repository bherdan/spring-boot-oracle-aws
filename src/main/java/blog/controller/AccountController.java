package blog.controller;

import blog.model.Account;
import blog.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountRepository accountRepository;

    @Autowired /* constructor injection is preferable as it is explicit */
    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Account> getByUsername(@RequestParam(value = "username", required = false) String username){
        if (username == null)
            return accountRepository.findAll();
        else
            return accountRepository.findByUsername(username);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Account createAccount(@RequestBody Account account){
        if (accountRepository.findByUsername(account.getUsername()).isEmpty())
        {
            return accountRepository.save(account);
        }
        else {
            System.out.println("Error creating new account. An account by that username already exists.");
            return null;
        }
    }

    //@RequestMapping(method = RequestMethod.PUT, "/")



    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Account getById(@PathVariable("id") Long id){
        return accountRepository.findById(id);
    }
}
