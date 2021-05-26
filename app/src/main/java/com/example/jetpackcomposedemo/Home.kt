package com.example.jetpackcomposedemo

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposedemo.model.BankCard
import com.example.jetpackcomposedemo.model.Transaction
import com.example.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme


class Home : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    HomePage()
                }
            }
        }
    }
}

@Composable
fun HomePage() {

    Scaffold(
        backgroundColor = Color.Gray.copy(alpha = .08f),
        topBar = { AppToolBar() },
        bottomBar = { AppBottomBar() }

    ) {
        Column(modifier = Modifier.padding(it)) {
            CardPager(createBankCards())
            ButtonRow()
            CreateHeadlineText("Recent Transaction")
            BuildTransactionList(createTransactionList())
        }

    }

}

@Composable
fun AppBottomBar() {
    BottomAppBar(
        backgroundColor = Color.White,
        elevation = 0.dp,
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            BottomNavigationItem(
                selected = false,
                onClick = { },
                alwaysShowLabel = false,
                icon = { Icon(Icons.Outlined.Home, contentDescription = null) },
                label = { Text(text = "Home", style = MaterialTheme.typography.overline) }
            )
            BottomNavigationItem(
                selected = false,
                alwaysShowLabel = false,
                onClick = { },
                icon = { Icon(Icons.Outlined.AccountBalanceWallet, contentDescription = null) },
                label = { Text(text = "Wallet", style = MaterialTheme.typography.overline) }
            )
            BottomNavigationItem(
                selected = false,
                onClick = { },
                alwaysShowLabel = false,
                icon = { Icon(Icons.Outlined.Payments, contentDescription = null) },
                label = { Text(text = "Transaction", style = MaterialTheme.typography.overline) }
            )
            BottomNavigationItem(
                selected = false,
                onClick = { },
                alwaysShowLabel = false,
                icon = { Icon(Icons.Outlined.PersonOutline, contentDescription = null) },
                label = { Text(text = "Profile", style = MaterialTheme.typography.overline) }
            )


        }
    }
}

/**
 * Create App Title bar
 */
@Composable
fun AppToolBar() {
    TopAppBar(
        title = {
            Text(
                "Account Detail",
                style = MaterialTheme.typography.subtitle1
            )
        },
        backgroundColor = Color.White,
        navigationIcon = {
            Icon(
                Icons.Outlined.AccountBalance,
                contentDescription = null,
                modifier = Modifier.padding(start = 10.dp),
            )
        },
        actions = {
            IconButton(
                modifier = Modifier.padding(end = 10.dp),
                onClick = {},
            ) {
                Icon(
                    Icons.Outlined.Notifications,
                    contentDescription = null,
                )
            }
        },
        elevation = 0.dp
    )
}

/**
 * Create horizontal Bank pager data
 */
fun createBankCards(): List<BankCard> {
    return listOf(
        BankCard(
            type = "Master",
            cardNumber = "**** **** **** **** 9090",
            cardBalance = 4680.0,
            cardName = "Tim cook",
            expDate = "07/24"
        ),
        BankCard(
            type = "Visa",
            cardNumber = "**** **** **** **** 1023",
            cardBalance = 86800.0,
            cardName = "Steve Jobs",
            expDate = "04/26"
        ),
        BankCard(
            type = "Master",
            cardNumber = "**** **** **** **** 1080",
            cardBalance = 50080.0,
            cardName = "Elon Musk",
            expDate = "05/27"
        ),
    )
}

/**
 * Create Transaction entries
 */
fun createTransactionList(): List<Transaction> {
    return listOf(
        Transaction(
            transactionTitle = "Food Panda",
            transactionSubtitle = "2 Burgers",
            amount = 15.99,
            time = "12:45 PM"
        ),
        Transaction(
            transactionTitle = "Apple pay",
            transactionSubtitle = "Subscription",
            amount = 99.00,
            time = "05:45 PM"
        ),
        Transaction(
            transactionTitle = "Netflix",
            transactionSubtitle = "Subscription",
            amount = 49.99,
            time = "04:45 PM"
        ),
        Transaction(
            transactionTitle = "Paypal",
            transactionSubtitle = "Send Money",
            amount = 80.99,
            time = "03:45 PM"
        ),
        Transaction(
            transactionTitle = "Amazon",
            transactionSubtitle = "Product Purchase",
            amount = 800.00,
            time = "03:49 PM"
        ),
        Transaction(
            transactionTitle = "Udemy",
            transactionSubtitle = "Bought Course",
            amount = 50.00,
            time = "06:49 PM"
        ),
    )
}

/**
 * Create horizontal card pager.
 */
@Composable
fun CardPager(cards: List<BankCard>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
            .height((getHeightDp(LocalContext.current) / 4).dp)
    ) {
        items(cards) { bankCard ->
            BankCard(bankCard)
        }
    }
}

/**
 * Create Bank card UI
 */
@Composable
fun BankCard(cardData: BankCard) {
    Card(
        elevation = 2.5.dp,
        modifier = Modifier
            .width(getWidthDp(LocalContext.current).dp)
            .fillMaxHeight()
            .clip(RoundedCornerShape(20.dp))
            .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .width(getWidthDp(LocalContext.current).dp)
                .fillMaxHeight()
        ) {
            //balance row
            BalanceRow(type = cardData.type, amount = cardData.cardBalance)
            CardNumber(cardData.cardNumber)
            CardHolderData(cardData = cardData)
        }
    }
}

/**
 * Create Card holder row.
 * Holder Name, Exp Date.
 */
@Composable
fun CardHolderData(cardData: BankCard) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth(),
    ) {
        CardHolderName(holderName = cardData.cardName)
        CardExpiry(expDate = cardData.expDate)
    }
}

@Composable
fun CardHolderName(holderName: String) {
    Column(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 5.dp, bottom = 10.dp),

        ) {
        Text(
            text = "ACCOUNT HOLDER",
            modifier = Modifier
                .padding(top = 20.dp, end = 20.dp),
            style = MaterialTheme.typography.caption.copy(
                color = Color.Gray

            )
        )
        Text(
            text = holderName,
            modifier = Modifier
                .padding(top = 5.dp, end = 20.dp),
            style = MaterialTheme.typography.caption
        )
    }
}

@Composable
fun CardExpiry(expDate: String) {
    Column(
        modifier = Modifier
            .padding(start = 20.dp, top = 5.dp, bottom = 10.dp),
    ) {
        Text(
            text = "EXP DATE",
            modifier = Modifier
                .padding(top = 20.dp, end = 20.dp),
            style = MaterialTheme.typography.caption.copy(
                color = Color.Gray

            )
        )
        Text(
            text = expDate,
            modifier = Modifier
                .padding(top = 5.dp),
            style = MaterialTheme.typography.caption.copy(
                textAlign = TextAlign.End

            )
        )
    }
}

/**
 * Card Number
 */
@Composable
fun CardNumber(cardNumber: String) {
    Row(
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = cardNumber,
            modifier = Modifier
                .padding(top = 20.dp, end = 20.dp)
                .fillMaxWidth(),
            style = MaterialTheme.typography.h6.copy(
                textAlign = TextAlign.End
            )
        )
    }
}

/**
 * Account Balance Row
 */
@Composable
fun BalanceRow(type: String, amount: Double) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .width(getWidthDp(LocalContext.current).dp)
            .padding(start = 20.dp, end = 20.dp, top = 10.dp)
    ) {
        Text(
            text = type,
            style = MaterialTheme.typography.body2.copy(color = Color.Gray)
        )
        Text(
            text = "$ $amount",
            style = MaterialTheme.typography.h6
        )
    }
}

/**
 * Build Transaction List
 */
@Composable
fun BuildTransactionList(transactions: List<Transaction>) {
    LazyColumn(
        modifier = Modifier.padding(top = 15.dp, start = 15.dp, end = 15.dp)
    ) {
        items(transactions) { transaction ->
            TransactionRow(transaction)
        }
    }
}

/**
 * Build Tranasction row
 */
@Composable
fun TransactionRow(transaction: Transaction) {
    Card(
        elevation = 1.dp,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
            .padding(start = 10.dp, top = 5.dp, end = 10.dp, bottom = 2.dp),

        ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TransactionIcon(type = transaction.transactionTitle.first().toString())
            //Build title
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text(
                        text = transaction.transactionTitle,
                        style = MaterialTheme.typography.caption
                    )
                    Text(
                        text = transaction.transactionSubtitle,
                        style = MaterialTheme.typography.overline.copy(color = Color.Gray)
                    )
                } //Build Amount & Time
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text(
                        text = "- $ " + transaction.amount.toString(),
                        style = MaterialTheme.typography.caption.copy(
                            color = Color.Red.copy(alpha = 0.5f)
                        )
                    )
                    Text(
                        text = transaction.time,
                        style = MaterialTheme.typography.overline.copy(color = Color.Gray)
                    )
                }
            }
        }
    }
}

@Composable
fun TransactionIcon(type: String) {
    Column(
        modifier = Modifier
            .padding(start = 10.dp)
            .wrapContentSize(Alignment.Center)
            .clip(CircleShape),
    ) {
        Box(
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
                .background(Color.Gray.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = type, style = MaterialTheme.typography.h5)
        }
    }
}

/**
 * Create Add Card & Top up Button
 */
@Composable
fun ButtonRow() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 10.dp)
    ) {
        CreateButton(
            text = "Add a card",
            onButtonClick = onAddCard,
            icon = Icons.Outlined.AddCircle
        )
        CreateButton(
            text = "Top Up", onButtonClick = onTopUp,
            icon = Icons.Outlined.Payment
        )
    }
}

var onAddCard: () -> Unit = {
    Log.d("Compose ", " onAddCard")
}
var onTopUp: () -> Unit = {
    Log.d("Compose ", " onTopUp")
}

/**
 * Create individual button
 * @param icon : Button icon
 * @param text : Button Text
 * @param onButtonClick : Button Click listener function
 */
@Composable
fun CreateButton(icon: ImageVector, text: String, onButtonClick: () -> Unit) {
    Button(
        onClick = onButtonClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Black,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .width((getWidthDp(context = LocalContext.current) / 2).dp)
            .height(70.dp)
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(painter = rememberVectorPainter(image = icon), contentDescription = null)
            Text(
                text,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(horizontal = 5.dp)
            )
        }
    }
}

@Composable
fun CreateHeadlineText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier.padding(horizontal = 20.dp)
    )
}

/**
 * @param context
 * @return the screen width in dp
 */
fun getWidthDp(context: Context): Float {
    val displayMetrics: DisplayMetrics = context.resources.displayMetrics
    return displayMetrics.widthPixels / displayMetrics.density
}

/**
 * @param context
 * @return the screen height in dp
 */
fun getHeightDp(context: Context): Float {
    val displayMetrics: DisplayMetrics = context.resources.displayMetrics
    return displayMetrics.heightPixels / displayMetrics.density
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeDemoTheme {
        HomePage()
    }
}