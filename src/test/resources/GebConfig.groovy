import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.safari.SafariDriver

environments {

    // You need to configure in Safari -> Develop -> Allowed Remote Automation
    safari {
        driver = { new SafariDriver() }
    }

    chrome {
        driver = { new ChromeDriver() }
    }

    chromeHeadless {
        driver = {
            ChromeOptions o = new ChromeOptions()
            o.addArguments('headless')
            new ChromeDriver(o)
        }
    }

    firefoxHeadless {
        driver = {
            FirefoxOptions o = new FirefoxOptions()
            o.addArguments('-headless')
            new FirefoxDriver(o)
        }
    }

    firefox {
        driver = { new FirefoxDriver() }
    }
}